package com.app.esfita;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.app.esfita.Pojo.LoanInfo;
import com.app.esfita.Retrofit.ApiService;
import com.app.esfita.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class OtherInfo extends AppCompatActivity {

    TextInputEditText brand;
    TextInputEditText price;
    TextInputEditText downPay;
    TextInputEditText loan;
    TextInputEditText tenure;
    TextInputEditText instal;
    TextInputEditText roi;
    TextInputEditText emi;
    TextInputEditText ltv;
    Button submit;

    String pan,name,dob,gender,mStatus,depend,housing,rented,empType,gross,net,empStability;
    String bankName,bankPeriod,geoLimit,address,pin,district,state;

    float prc = 0;
    float downpay = 0;
    float loanAmt = 0;
    float rate = 0;
    float rValue = 0;
    float ltV = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_info);

        ActionBar ab = getSupportActionBar();
        assert ab!= null;
        ab.setTitle("Other info");
        ab.setDisplayHomeAsUpEnabled(true);

        submit = findViewById(R.id.submit);
        brand = findViewById(R.id.brand);
        price = findViewById(R.id.price);
        downPay = findViewById(R.id.downPay);
        loan = findViewById(R.id.loan);
        tenure = findViewById(R.id.tenure);
        instal = findViewById(R.id.instal);;
        roi = findViewById(R.id.roi);
        emi = findViewById(R.id.emi);
        ltv = findViewById(R.id.ltv);

        ltv.setEnabled(false);

        Intent i = getIntent();

            pan = i.getExtras().getString("pan","");
            name = i.getExtras().getString("name","");
            dob = i.getExtras().getString("dob","");
            gender = i.getExtras().getString("gender","");
            mStatus = i.getExtras().getString("mStatus","");
            depend = i.getExtras().getString("depend","");
            housing = i.getExtras().getString("housing","");
            rented = i.getExtras().getString("rented","");
            empType = i.getExtras().getString("empType","");
            gross = i.getExtras().getString("gross","");
            net = i.getExtras().getString("net","");
            empStability = i.getExtras().getString("empStability","");
            bankName = i.getExtras().getString("bankName","");
            bankPeriod =  i.getExtras().getString("bankPeriod","");
            geoLimit = i.getExtras().getString("geoLimit","");
            address = i.getExtras().getString("address","");
            pin = i.getExtras().getString("pin","");
            district = i.getExtras().getString("district","");
            state = i.getExtras().getString("state","");


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                  calculate();
            }
        };

        price.addTextChangedListener(textWatcher);
        downPay.addTextChangedListener(textWatcher);
        roi.addTextChangedListener(textWatcher);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog prg = new ProgressDialog(OtherInfo.this);
                prg.setMessage("Sending data...");
                prg.setCancelable(false);
                prg.show();

                if(brand.getText().toString().equals("")){

                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter brand",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(price.getText().toString().equals("")){

                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter price",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(downPay.getText().toString().equals("")){

                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter down payment",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(loan.getText().toString().equals("")){

                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter loan amount",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(tenure.getText().toString().equals("")){

                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter tenure",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(instal.getText().toString().equals("")){

                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter installment",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(roi.getText().toString().equals("")){
                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter rate of interest",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(emi.getText().toString().equals("")){
                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter EMI",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else{

                    Retrofit retrofit = RetrofitClient.getInstance();
                    ApiService apiService = retrofit.create(ApiService.class);

                    JsonObject jsonObject = new JsonObject();
                    jsonObject.addProperty("pan",pan);
                    jsonObject.addProperty("dateOfBirth",dob);
                    jsonObject.addProperty("martial_status",mStatus);
                    jsonObject.addProperty("dependants",depend);
                    jsonObject.addProperty("residence",housing);
                    jsonObject.addProperty("staying_year",rented);
                    jsonObject.addProperty("employmentType",empType);
                    jsonObject.addProperty("monthlySalary",net);
                    jsonObject.addProperty("stability",empStability);
                    jsonObject.addProperty("bankName",bankName);
                    jsonObject.addProperty("bank_detail",bankPeriod);
                    jsonObject.addProperty("closingBalance","15000");
                    jsonObject.addProperty("vehiclePrice",price.getText().toString());
                    jsonObject.addProperty("downPayment",downPay.getText().toString());
                    jsonObject.addProperty("interest",roi.getText().toString());
                    jsonObject.addProperty("tenure",tenure.getText().toString());
                    jsonObject.addProperty("chequeBounce","0");

                    apiService.getLoanInfo(jsonObject).subscribeOn(Schedulers.io())
                                                      .observeOn(AndroidSchedulers.mainThread())
                                                      .subscribe(new Observer<LoanInfo>() {
                                                          @Override
                                                          public void onSubscribe(Disposable d) {

                                                          }
                                                          @Override
                                                          public void onNext(LoanInfo loanInfo) {

                                                              if(loanInfo.getStatus().equals("Success")){

                                                                  Intent i = new Intent(OtherInfo.this,Success.class);
                                                                  i.putExtra("ts",loanInfo.getTotalScore());
                                                                  i.putExtra("pr",loanInfo.getPrediction());
                                                                  i.putExtra("ps",loanInfo.getPersonalScore());
                                                                  i.putExtra("es",loanInfo.getEmploymentScore());
                                                                  i.putExtra("bs",loanInfo.getBankingScore());
                                                                  i.putExtra("ci",loanInfo.getCibilScore());
                                                                  startActivity(i);
                                                                  finish();
                                                              }
                                                              else{
                                                                  prg.dismiss();
                                                                  TastyToast.makeText(getApplicationContext(),"Server error",
                                                                          TastyToast.LENGTH_SHORT,TastyToast.ERROR).show();
                                                              }
                                                          }

                                                          @Override
                                                          public void onError(Throwable e) {

                                                              prg.dismiss();
                                                              TastyToast.makeText(getApplicationContext(),e.getMessage(),
                                                                      TastyToast.LENGTH_SHORT,TastyToast.ERROR).show();
                                                          }

                                                          @Override
                                                          public void onComplete() {

                                                          }
                                                      });
                }
            }
        });

    }

    private void calculate(){

        prc = Float.parseFloat(!price.getText().toString().equals("")?
                    price.getText().toString() : "0");

        downpay = Float.parseFloat(!downPay.getText().toString().equals("")?
                downPay.getText().toString() : "0");

        rate = Float.parseFloat(!roi.getText().toString().equals("")?roi.getText().toString():"0");

        loanAmt = prc - downpay;

        ltV = (loanAmt / prc) * 100;

        loan.setText(String.valueOf(loanAmt));
        ltv.setText(String.valueOf(ltV));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home){

            Intent i = new Intent(OtherInfo.this,PersonalInfo.class);
            startActivity(i);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(OtherInfo.this);
        builder.setMessage("Do you want to leave.");
        builder.setCancelable(true);
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.cancel();
            }
        });
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}