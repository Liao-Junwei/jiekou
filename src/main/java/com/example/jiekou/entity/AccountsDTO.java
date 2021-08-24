package com.example.jiekou.entity;


import lombok.Data;

import java.util.List;

@Data
public class AccountsDTO {

    private List<String> accounts;

    public List<String> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<String> accounts) {
        this.accounts = accounts;
    }
}
