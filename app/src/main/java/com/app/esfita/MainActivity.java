package com.app.esfita;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.esfita.Pojo.LoginResponse;
import com.app.esfita.Retrofit.ApiService;
import com.app.esfita.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {


    TextInputEditText email,pwd;
    CircularProgressButton btn_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar  ab = getSupportActionBar();
        assert ab != null;
        ab.hide();

        email = findViewById(R.id.email);
        pwd = findViewById(R.id.pwd);
        btn_id =  findViewById(R.id.btn_id);


        btn_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str1 = email.getText().toString();
                String str2 = pwd.getText().toString();

                btn_id.startAnimation();
                if(str1.equals("")){

                    btn_id.revertAnimation();
                    TastyToast.makeText(getApplicationContext(),"Enter email",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(str2.equals("")){

                    btn_id.revertAnimation();
                    TastyToast.makeText(getApplicationContext(),"Enter password",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else{

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("userId",str1);
                    jsonObject.addProperty("password",str2);

                    Retrofit retrofit = RetrofitClient.getInstance();
                    ApiService apiService = retrofit.create(ApiService.class);

                    apiService.getResponse(jsonObject).subscribeOn(Schedulers.io())
                            .subscribe(new Observer<LoginResponse>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onNext(final LoginResponse value) {

                                    if(value.getStatus() != null){
                                        final String status = value.getStatus();

                                        if(status.equals("Success")){

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    btn_id.revertAnimation();
                                                    email.setText("");
                                                    pwd.setText("");
                                                    Intent i = new Intent(MainActivity.this,LoanMenu.class);
                                                    startActivity(i);
                                                    finish();
                                                }
                                            });
                                        }
                                        else{

                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {

                                                    btn_id.revertAnimation();
                                                    TastyToast.makeText(getApplicationContext(),status,TastyToast.LENGTH_LONG,
                                                            TastyToast.ERROR).show();
                                                }
                                            });
                                        }
                                    }
                                }

                                @Override
                                public void onError(final Throwable e) {

                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            TastyToast.makeText(getApplicationContext(),e.getMessage(),TastyToast.LENGTH_SHORT,
                                                    TastyToast.ERROR).show();
                                        }
                                    });
                                }

                                @Override
                                public void onComplete() {

                                }
                            });
                   }
            }
        });
    }


}