package com.example.jiekou.entity;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
public class UserInfoDTO {

    private String id;

    @NotBlank(message = "账号不能为空")
    @Size(min = 6, max = 11, message = "账号长度必须是6到11个字符")
    private String account;

    @NotBlank(message = "密码不能为空")
    @Size(min = 4, message = "密码长度不少于4个字符")
    private String password;

    @NotBlank(message = "用户名不能为空")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void  setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
