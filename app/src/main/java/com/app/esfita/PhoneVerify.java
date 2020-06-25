package com.app.esfita;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.app.esfita.Pojo.OtpResponse;
import com.app.esfita.Retrofit.ApiService;
import com.app.esfita.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import in.aabhasjindal.otptextview.OtpTextView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PhoneVerify extends AppCompatActivity {

    TextInputEditText phone;
    Button no;
    CircularProgressButton verify;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verify);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.hide();

        phone = findViewById(R.id.phone);
        no = findViewById(R.id.no);
        verify = findViewById(R.id.verify);

        lottieAnimationView = findViewById(R.id.animat);
        lottieAnimationView.setImageAssetsFolder("images/");
        lottieAnimationView.setAnimation("images/ver.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PhoneVerify.this,LoanMenu.class);
                startActivity(i);
                finish();
            }
        });

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                verify.startAnimation();
                String no = phone.getText().toString();

                if(no.equals("")){

                    verify.revertAnimation();
                    TastyToast.makeText(getApplicationContext(),"Enter number",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(no.length() != 10){

                    verify.revertAnimation();
                    TastyToast.makeText(getApplicationContext(),"Invalid number",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else{

                    verify(no);
                }
            }
        });
    }

    private void verify(String number){

        Retrofit retrofit= RetrofitClient.getInstance();
        ApiService apiService = retrofit.create(ApiService.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("mobNo",number);

        apiService.getOtp(jsonObject).subscribeOn(Schedulers.io())
                                     .subscribe(new Observer<OtpResponse>() {
                                         @Override
                                         public void onSubscribe(Disposable d) {

                                         }

                                         @Override
                                         public void onNext(final OtpResponse value) {

                                             if(value != null && value.getStatus().equals("Success")){

                                                 runOnUiThread(new Runnable() {
                                                     @Override
                                                     public void run() {

                                                         verify.revertAnimation();
                                                         Toast.makeText(getApplicationContext(),value.getOtp(),
                                                                 Toast.LENGTH_SHORT).show();

                                                           Intent in = new Intent(PhoneVerify.this,Verify.class);
                                                           in.putExtra("otp",value.getOtp());
                                                           startActivity(in);
                                                           finish();
                                                     }
                                                 });
                                             }
                                         }

                                         @Override
                                         public void onError(final Throwable e) {

                                             runOnUiThread(new Runnable() {
                                                 @Override
                                                 public void run() {
                                                     verify.revertAnimation();
                                                     TastyToast.makeText(getApplicationContext(),e.getMessage(),
                                                             TastyToast.LENGTH_LONG,TastyToast.ERROR).show();
                                                 }
                                             });
                                         }

                                         @Override
                                         public void onComplete() {

                                         }
                                     });
    }

}