package com.example.jiekou.service;


import com.example.jiekou.entity.UserInfoDTO;
import com.example.jiekou.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class UserInfoService {

    @Autowired
    UserInfoMapper userInfoMapper;

    /**
     * 通过 name 查询用户信息
     * @param name
     * @return
     */
    public List<UserInfoDTO> queryUser(String name){
        return userInfoMapper.queryUserInfoByName(name);
    }


    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    public int addUser(UserInfoDTO userInfo) {
        return userInfoMapper.insertUserInfo(userInfo);
    }


    /**
     * 查询所有用户信息
     * @return
     */
    public List<UserInfoDTO> queryAllUser() {
        return userInfoMapper.queryAllUser();
    }


    /**
     * 修改用户信息（用account改name和password）
     * @param userInfo
     * @return
     */
    public int modifyUserInfo(UserInfoDTO userInfo) {

        UserInfoDTO userInfoDTO = userInfoMapper.queryUserInfoByAccount(userInfo.getAccount());

        if (userInfoDTO != null) {
            // 字符串值比较用equals()
            if (userInfo.getName().equals(userInfoDTO.getName()) &&
                    userInfo.getPassword().equals(userInfoDTO.getPassword())){
                return -2; //用户名和密码未改动
            }
            return userInfoMapper.updateUserInfo(userInfo);
        } else {
            return -1; //账号不存在
        }

    }

    /**
     * 多条件查询用户信息（账号、姓名、密码）
     * @param queryInfo
     * @return
     */
    public List<UserInfoDTO> queryUsers(HashMap<String, Object> queryInfo){
        return userInfoMapper.queryUsers(queryInfo);
    }


    public String[] getHead() {
        String[] head = {"id", "账号", "姓名", "密码"};
        return head;
    }

    public String getFileName() {
        return "sqlData";
    }

    public List<String[]> getValues(List<String> accounts) {
        ArrayList<String[]> values = new ArrayList<>();
        for (String account : accounts) {

            UserInfoDTO userInfoDTO = userInfoMapper.queryUserInfoByAccount(account);
            String[] value = {userInfoDTO.getId(), account, userInfoDTO.getName(), userInfoDTO.getPassword()};
            values.add(value);
        }
        return values;
    }


}
