package com.app.esfita.Pojo;

import com.google.gson.annotations.SerializedName;

public class OtpResponse {

    @SerializedName("status")
    String status;

    @SerializedName("otp")
    String otp;

    public OtpResponse(){

    }

    public OtpResponse(String status, String otp) {
        this.status = status;
        this.otp = otp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
