package com.example.eduaerospike.models;

import org.springframework.data.annotation.Id;

public class Deposit {
    @Id
    private Integer id;
    private Integer clientId;
    private String  name;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public String getName() {
        return name;
    }
}
