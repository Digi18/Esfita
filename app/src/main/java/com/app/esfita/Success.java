package com.app.esfita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class Success extends AppCompatActivity {

    TextView totalScore,pred,persScore,empScore,bScore,cibil;
    String ts,pr,ps,es,bs,ci;
    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("Success");

        lottieAnimationView = findViewById(R.id.animat);
        lottieAnimationView.setImageAssetsFolder("images/");
        lottieAnimationView.setAnimation("images/ryt.json");
        lottieAnimationView.loop(false);
        lottieAnimationView.playAnimation();

        totalScore = findViewById(R.id.totalScore);
        pred = findViewById(R.id.pred);
        persScore = findViewById(R.id.persScore);
        empScore = findViewById(R.id.empScore);
        bScore = findViewById(R.id.bScore);;
        cibil = findViewById(R.id.cibil);

        Intent i = getIntent();
        ts = i.getExtras().getString("ts","");
        pr = i.getExtras().getString("pr","");
        ps = i.getExtras().getString("ps","");
        es = i.getExtras().getString("es","");
        bs = i.getExtras().getString("bs","");
        ci = i.getExtras().getString("ci","");

        totalScore.setText(ts);
        pred.setText(pr);
        persScore.setText(ps);
        empScore.setText(es);
        bScore.setText(bs);
        cibil.setText(ci);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() ==  android.R.id.home){

            Intent i = new Intent(Success.this,MainActivity.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}