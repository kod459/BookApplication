package com.example.kod45.bookapplication.entity;

import com.google.firebase.database.IgnoreExtraProperties;

/**
 * Created by kod45 on 28/03/2018.
 */

@IgnoreExtraProperties
public class User {
    private String name, email, password, creditNumber, address, type;

    public User() {}

    public User(String name, String email, String password, String paymentDetails, String shippingDetails, String type) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.creditNumber = paymentDetails;
        this.address = shippingDetails;
        this.type = type;
    }

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreditNumber() {
        return creditNumber;
    }

    public void setCreditNumber(String creditNumber) {
        this.creditNumber = creditNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
