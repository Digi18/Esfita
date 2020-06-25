package com.app.esfita;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.app.esfita.Pojo.PanDetails;
import com.app.esfita.Retrofit.ApiService;
import com.app.esfita.Retrofit.RetrofitClient;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;

import br.com.simplepass.loadingbutton.customViews.CircularProgressButton;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class PersonalInfo extends AppCompatActivity {

    Spinner gender,maritalStatus,housing,empType,empStability,bankName,bankPeriod,geoLimit;
    TextInputEditText pan,name,mydob,dependents,houseYrs,grossSalary,netSalary,address,pin,district,state;
    Button panVerify,next;
    ProgressDialog prg,prog;

    String[] gen = { "Select gender","Male", "Female"};
    String[] mar = {"Select marital status","Single","Married"};
    String[] house = {"Select house type","Owned","Rented"};
    String[] emp = {"Select employment type","Salaried","Self Emp. Professional","Self Employed","Others"};
    String[] stability = {"Select employment stability","Salaried with over 3 Years","Salaried with over 2 Years",
                          "Salaried with over 1 Year","other"};
    String[] banks = {"Select bank","HDFC","AXIS","ICICI"};
    String[] period = {"Select banking period","Account over 2 years","Account over 1 and above",
                        "Account over 6 months","other"};
    String[] geo = {"Select geo limit","Less than 15km","Greater than 15km"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);

        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setTitle("Personal info");

        //Edittext
        pan = findViewById(R.id.pan);
        name = findViewById(R.id.name);
        mydob = findViewById(R.id.dob);
        dependents = findViewById(R.id.dependents);
        houseYrs = findViewById(R.id.houseYrs);
        grossSalary = findViewById(R.id.grossSalary);
        netSalary = findViewById(R.id.netSalary);
        address = findViewById(R.id.address);
        pin = findViewById(R.id.pin);
        district = findViewById(R.id.district);
        state = findViewById(R.id.state);

        //button
        panVerify = findViewById(R.id.panVerify);
        next = findViewById(R.id.next);

        //Spinners
        gender = findViewById(R.id.gender);
        maritalStatus = findViewById(R.id.maritalStatus);
        housing = findViewById(R.id.housing);
        empType = findViewById(R.id.empType);
        empStability = findViewById(R.id.empStability);
        bankName = findViewById(R.id.bankName);
        bankPeriod = findViewById(R.id.bankPeriod);
        geoLimit = findViewById(R.id.geoLimit);

        ArrayAdapter genderAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,gen);
        genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gender.setAdapter(genderAdapter);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter marAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,mar);
        marAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        maritalStatus.setAdapter(marAdapter);

        maritalStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter houseAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,house);
        houseAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        housing.setAdapter(houseAdapter);

        housing.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter empAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,emp);
        empAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        empType.setAdapter(empAdapter);

        empType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter stableAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,stability);
        stableAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        empStability.setAdapter(stableAdapter);

        empStability.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter banksAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,banks);
        banksAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankName.setAdapter(banksAdapter);

        bankName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter periodAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,period);
        periodAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bankPeriod.setAdapter(periodAdapter);

        bankPeriod.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter geoAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,geo);
        geoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        geoLimit.setAdapter(geoAdapter);

        geoLimit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        panVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                prg = new ProgressDialog(PersonalInfo.this);
                prg.setMessage("Getting info...");
                prg.setCancelable(false);
                prg.show();

                if(pan.getText().toString().equals("")){

                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Enter pan no",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(pan.getText().toString().length() != 10){

                    prg.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Invalid pan no",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else{

                   sendPan(pan.getText().toString());
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(pan.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter pan",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(pan.getText().toString().length() != 10){

                    TastyToast.makeText(getApplicationContext(),"Invalid pan no",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(name.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter name",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(mydob.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter D.O.B",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(gender.getSelectedItem().toString().equals("Select gender")){

                    TastyToast.makeText(getApplicationContext(),"Select gender",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(maritalStatus.getSelectedItem().toString().equals("Select marital status")){

                    TastyToast.makeText(getApplicationContext(),"Select marital status",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(dependents.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter No. of dependents",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(housing.getSelectedItem().toString().equals("Select house type")){
                    prog.dismiss();
                    TastyToast.makeText(getApplicationContext(),"Select house type",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(houseYrs.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter No. of years",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(empType.getSelectedItem().toString().equals("Select employment type")){

                    TastyToast.makeText(getApplicationContext(),"Select employment type",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(grossSalary.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter gross salary",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(netSalary.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter net salary",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(empStability.getSelectedItem().toString().equals("Select employment stability")){

                    TastyToast.makeText(getApplicationContext(),"Select employment stability",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(bankName.getSelectedItem().toString().equals("Select bank")){

                    TastyToast.makeText(getApplicationContext(),"Select bank",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(bankPeriod.getSelectedItem().toString().equals("Select banking period")){

                    TastyToast.makeText(getApplicationContext(),"Select banking period",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(geoLimit.getSelectedItem().toString().equals("Select geo limit")){

                    TastyToast.makeText(getApplicationContext(),"Select geo limit",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(address.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter address",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(pin.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter pin",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(pin.getText().toString().length() != 6){

                    TastyToast.makeText(getApplicationContext(),"Invalid pin",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(district.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter district",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else if(state.getText().toString().equals("")){

                    TastyToast.makeText(getApplicationContext(),"Enter state",TastyToast.LENGTH_SHORT,
                            TastyToast.ERROR).show();
                }
                else{

                    Intent i = new Intent(PersonalInfo.this,OtherInfo.class);
                    i.putExtra("pan",pan.getText().toString());
                    i.putExtra("name",name.getText().toString());
                    i.putExtra("dob",mydob.getText().toString());
                    i.putExtra("gender",gender.getSelectedItem().toString());
                    i.putExtra("mStatus",maritalStatus.getSelectedItem().toString());
                    i.putExtra("depend",dependents.getText().toString());
                    i.putExtra("housing",housing.getSelectedItem().toString());
                    i.putExtra("rented",houseYrs.getText().toString());
                    i.putExtra("empType",empType.getSelectedItem().toString());
                    i.putExtra("gross",grossSalary.getText().toString());
                    i.putExtra("net",netSalary.getText().toString());
                    i.putExtra("empStability",empStability.getSelectedItem().toString());
                    i.putExtra("bankName",bankName.getSelectedItem().toString());
                    i.putExtra("bankPeriod",bankPeriod.getSelectedItem().toString());
                    i.putExtra("geoLimit",geoLimit.getSelectedItem().toString());
                    i.putExtra("address",address.getText().toString());
                    i.putExtra("pin",pin.getText().toString());
                    i.putExtra("district",district.getText().toString());
                    i.putExtra("state",state.getText().toString());
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private void sendPan(String str1){

        Retrofit retrofit = RetrofitClient.getInstance();
        ApiService apiService = retrofit.create(ApiService.class);

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("panNo",str1);

        apiService.getDetails(jsonObject).subscribeOn(Schedulers.io())
                                         .observeOn(AndroidSchedulers.mainThread())
                                         .subscribe(new Observer<PanDetails>() {
                                             @Override
                                             public void onSubscribe(Disposable d) {

                                             }

                                             @Override
                                             public void onNext(PanDetails value) {

                                                 if(value.getStatus().equals("Success")){

                                                     prg.dismiss();

                                                     name.setText(value.getPanHolderName());
                                                     mydob.setText(value.getDateOfBirth());
                                                     address.setText(value.getAddress());
                                                     pin.setText(value.getPincode());
                                                     if(value.getGender().equals("Male")){
                                                         gender.setSelection(1);
                                                     }
                                                     else{
                                                         gender.setSelection(2);
                                                     }

                                                     if(value.getOccupation().equals("Salaried")){
                                                         empType.setSelection(1);
                                                     }
                                                     else if(value.getOccupation().equals("Self Emp. Professional")){
                                                         empType.setSelection(2);
                                                     }
                                                     else if(value.getOccupation().equals("Self Employed")){
                                                         empType.setSelection(3);
                                                     }
                                                     else{
                                                         empType.setSelection(4);
                                                     }
                                                 }
                                                 else{
                                                     prg.dismiss();
                                                     name.setText(value.getPanHolderName());
                                                 }
                                             }

                                             @Override
                                             public void onError(final Throwable e) {
                                                 prg.dismiss();
                                                 TastyToast.makeText(getApplicationContext(),e.getMessage(),
                                                         TastyToast.LENGTH_LONG,TastyToast.ERROR).show();

                                             }

                                             @Override
                                             public void onComplete() {

                                             }
                                         });
    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(PersonalInfo.this);
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