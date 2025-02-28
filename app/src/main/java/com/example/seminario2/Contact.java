package com.example.seminario2;

import java.io.Serializable;

public class Contact implements Serializable {
    private long id;
    private String name;
    private String phone_number;

    public Contact(long id, String name, String phone_number) {
        this.id = id;
        this.name = name;
        this.phone_number = phone_number;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }
}