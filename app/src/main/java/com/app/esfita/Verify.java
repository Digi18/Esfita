package com.app.esfita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import in.aabhasjindal.otptextview.OtpTextView;

public class Verify extends AppCompatActivity {

    CircularProgressButton verify;
    OtpTextView otpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Verify");
        ab.setDisplayHomeAsUpEnabled(true);

        verify  = findViewById(R.id.verify);
        otpView = findViewById(R.id.otpView);

        Intent i = getIntent();
        String data = i.getExtras().getString("otp","");

        otpView.setOTP(data);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i =new Intent(Verify.this,PersonalInfo.class);
                startActivity(i);
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){

            Intent i = new Intent(Verify.this,PhoneVerify.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}