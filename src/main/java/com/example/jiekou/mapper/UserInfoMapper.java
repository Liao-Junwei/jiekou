package com.example.jiekou.mapper;


import com.example.jiekou.entity.UserInfoDTO;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;

public interface UserInfoMapper {

    List<UserInfoDTO> queryUserInfoByName(@Param("name") String name);

    UserInfoDTO queryUserInfoByAccount(@Param("account") String account);

    int insertUserInfo(@Param("userInfo") UserInfoDTO userInfo);

    List<UserInfoDTO> queryAllUser();

    int updateUserInfo(@Param("userInfo") UserInfoDTO userInfo);

    List<UserInfoDTO> queryUsers(@Param("queryInfo") HashMap<String, Object> queryInfo);
}
