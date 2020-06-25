package com.app.esfita.Pojo;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    String status;

    public LoginResponse(){

    }

    public LoginResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
