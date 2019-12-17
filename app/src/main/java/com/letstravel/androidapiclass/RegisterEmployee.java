package com.letstravel.androidapiclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letstravel.androidapiclass.interfaceemployee.EmployeeAPI;
import com.letstravel.androidapiclass.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterEmployee extends AppCompatActivity {

    private final static String base_url="http://dummy.restapiexample.com/api/v1/";
    private EditText etname, etsalary, etage;
    private Button btnRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);

        etname=findViewById(R.id.etname);
        etsalary=findViewById(R.id.etsalary);
        etage=findViewById(R.id.etage);
        btnRegister=findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });
    }
private void Register(){
        String name =etname.getText().toString();
        Float salary=Float.parseFloat(etsalary.getText().toString());
        int age= Integer.parseInt(etage.getText().toString());

    EmployeeCUD employeeCUD=new EmployeeCUD(name, salary, age);

    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    EmployeeAPI employeeAPI=retrofit.create(EmployeeAPI.class);
    Call<Void> voidCall=employeeAPI.registerEmployee(employeeCUD);

    voidCall.enqueue(new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Toast.makeText(RegisterEmployee.this, "You have been successfully registered", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Toast.makeText(RegisterEmployee.this, "Error", Toast.LENGTH_SHORT).show();
        }
    });
}

}
