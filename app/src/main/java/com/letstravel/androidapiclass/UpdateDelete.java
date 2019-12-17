package com.letstravel.androidapiclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letstravel.androidapiclass.interfaceemployee.EmployeeAPI;
import com.letstravel.androidapiclass.model.Employee;
import com.letstravel.androidapiclass.model.EmployeeCUD;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDelete extends AppCompatActivity {

    private final static String base_url="http://dummy.restapiexample.com/api/v1/";
    private Button btnEmployeeSearch, btnEmployeeUpdate,btnEmployeeDelete;
    private EditText etEmployeeNo,etEmployeeName,etEmployeeSalary,etEmployeeAge;
    Retrofit retrofit;
    EmployeeAPI employeeAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        btnEmployeeSearch=findViewById(R.id.btnEmployeeSearch);
        btnEmployeeUpdate=findViewById(R.id.btnEmployeeUpdate);
        btnEmployeeDelete=findViewById(R.id.btnEmployeeDelete);

        etEmployeeNo=findViewById(R.id.etEmployeeNo);
        etEmployeeName=findViewById(R.id.etEmployeeName);
        etEmployeeSalary=findViewById(R.id.etEmployeeSalary);
        etEmployeeAge=findViewById(R.id.etEmployeeAge);


        btnEmployeeSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData();
            }
        });


        btnEmployeeUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateEmployee();
            }
        });

        btnEmployeeDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteEmployee();
            }
        });
    }

    private void CreateInstance(){
        retrofit=new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        employeeAPI =retrofit.create(EmployeeAPI.class);
    }

    private void LoadData(){
        CreateInstance();
        Call<Employee>listCall=employeeAPI.getEmployeeByID(Integer.parseInt(etEmployeeNo.getText().toString()));

        listCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                etEmployeeName.setText(response.body().getEmployee_name());
                etEmployeeSalary.setText(response.body().getEmployee_salary());
                etEmployeeAge.setText(Integer.toString(response.body().getEmployee_age()));
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void updateEmployee(){
        CreateInstance();

        EmployeeCUD employeeCUD =new EmployeeCUD(etEmployeeName.getText().toString(),
                Float.parseFloat(etEmployeeSalary.getText().toString()),
                Integer.parseInt(etEmployeeAge.getText().toString()));

        Call<Void> voidCall = employeeAPI.updateEmployee(Integer.parseInt(etEmployeeNo.getText().toString()),employeeCUD);
        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDelete.this, "Updated", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void deleteEmployee(){
        CreateInstance();

        Call<Void> voidCall=employeeAPI.deleteEmployee(Integer.parseInt(etEmployeeNo.getText().toString()));

        voidCall.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Toast.makeText(UpdateDelete.this, "Successfully deleted", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(UpdateDelete.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
