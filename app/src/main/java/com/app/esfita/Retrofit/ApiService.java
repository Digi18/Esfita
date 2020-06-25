package com.app.esfita.Retrofit;

import com.app.esfita.Pojo.LoanInfo;
import com.app.esfita.Pojo.LoginResponse;
import com.app.esfita.Pojo.OtpResponse;
import com.app.esfita.Pojo.PanDetails;
import com.google.gson.JsonObject;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {

  /*  @POST("userLoginVerification")
    Call<LoginResponse> getResponse(@Body JsonObject jsonObject);  */

    @POST("userLoginVerification")
    Observable<LoginResponse> getResponse(@Body JsonObject jsonObject);

    @POST("sendOTP")
    Observable<OtpResponse> getOtp(@Body JsonObject jsonObject);

    @POST("getPanDetails")
    Observable<PanDetails> getDetails(@Body JsonObject jsonObject);

    @POST("getLoanInfo")
    Observable<LoanInfo> getLoanInfo(@Body JsonObject jsonObject);
}
