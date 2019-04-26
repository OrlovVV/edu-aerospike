package com.example.eduaerospike.models;

public class Account {
    private Integer id;
    private Integer depositId;
    private String accounts;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDepositId(Integer depositId) {
        this.depositId = depositId;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public Integer getId() {
        return id;
    }

    public Integer getDepositId() {
        return depositId;
    }

    public String getAccounts() {
        return accounts;
    }
}
