package com.example.jiekou.controller;


import com.example.jiekou.common.exception.Result;
import com.example.jiekou.common.utils.CsvExportUtil;
import com.example.jiekou.entity.AccountsDTO;
import com.example.jiekou.entity.UserInfoDTO;
import com.example.jiekou.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("userInfo")
public class UserInfoController {
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 新增用户
     * @param userInfo
     * @return
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody @Valid UserInfoDTO userInfo){

        int result = userInfoService.addUser(userInfo);
        return Result.success(result);
    }

    /**
     * 用 name 查询用户信息（1、用户不存在 2、查询单个用户信息 3、查询同账号多个用户信息）
     * @param name
     * @return
     */
    @GetMapping("/queryUser/{name}")
    public Result queryUser(@PathVariable("name") String name){

        List<UserInfoDTO> allUser = userInfoService.queryAllUser();
        List<String> nameList = allUser.stream().map(UserInfoDTO::getName).collect(Collectors.toList());
        if (!nameList.contains(name)){
            return Result.failed("用户 " + name + " 不存在!");
        }
        List<UserInfoDTO> userInfo = userInfoService.queryUser(name);
        return Result.success(userInfo);
    }


    /**
     * 修改用户信息（用户名和密码）
     * @param userInfo
     * @return
     */
    @PostMapping("/modifyUserInfo")
    public Result modifyUserInfo(@RequestBody @Valid UserInfoDTO userInfo) {
        int result = userInfoService.modifyUserInfo(userInfo);
        if (result == -1) {
            return Result.failed(userInfo.getAccount() + " 账号不存在！");
        }
        if (result == -2) {
            return Result.failed("用户名和密码未改动！");
        }

        return Result.success(result);
    }

    /**
     * 多条件查询用户信息（账号、姓名、密码）
     * @param account
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/query")
    public Result query(@RequestParam(name = "account", required = false) String account,
                        @RequestParam(name = "name", required = false) String name,
                        @RequestParam(name = "password", required = false) String password) {

        HashMap<String, Object> queryInfo = new HashMap<>();
        queryInfo.put("account", account);
        queryInfo.put("name", name);
        queryInfo.put("password", password);

        List<UserInfoDTO> users = userInfoService.queryUsers(queryInfo);

        if (users.isEmpty()){
            return Result.failed("查询无结果");
        }
        return Result.success(users);
    }


    /**
     * @param response
     * @return
     * @Description 根据账号批量导出CSV
     **/
    @PostMapping("/exportCSV")
    public String  exportUsersCSV(HttpServletResponse response, @RequestBody AccountsDTO accountsDTO) throws IOException {

        String fileName = userInfoService.getFileName();
        String[] head = userInfoService.getHead();
        List<String[]> values = userInfoService.getValues(accountsDTO.getAccounts());

        File file = CsvExportUtil.makeTempCSV(fileName, head, values);
        System.out.println(file);

        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment;fileName=" + fileName +".csv");
        CsvExportUtil.downloadFile(response, file);
        return null;
    }

    /**
     * @return
     * @Description 上传CSV
     * @Param file
     **/
    @PostMapping(value = "/upload")
    public String upload(@RequestParam("multipartFile") MultipartFile multipartFile) {
        try {
            //上传内容不能为空
            if (multipartFile.isEmpty()) {
                return "500";
            }
            File file = CsvExportUtil.uploadFile(multipartFile);
//            List<List<String>> userRoleLists = CsvExportUtil.readCSV(file.getPath(), 4);
            List<List<String>> userRoleLists = CsvExportUtil.readCSV(file.getPath(), 4);
            System.out.println(userRoleLists);
            file.delete();
            return "200";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "400";
    }

}

