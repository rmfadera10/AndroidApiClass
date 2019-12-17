package com.letstravel.androidapiclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.letstravel.androidapiclass.interfaceemployee.EmployeeAPI;
import com.letstravel.androidapiclass.model.Employee;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    private EditText etsearchbyid;
    private Button btnsearch;
    private TextView txtsearchbyid;
    private static String base_url = "http://dummy.restapiexample.com/api/v1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        etsearchbyid=findViewById(R.id.searchbyid);
        btnsearch=findViewById(R.id.btnsearch);
        txtsearchbyid=findViewById(R.id.txtsearchbyid);

        btnsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadDatabyID();
            }
        });
    }

    private void LoadDatabyID(){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        EmployeeAPI employeeAPI1=retrofit.create(EmployeeAPI.class);

        Call<Employee> employeeCall=employeeAPI1.getEmployeeByID(Integer.parseInt(etsearchbyid.getText().toString()));

        employeeCall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchActivity.this, response.body().toString(), Toast.LENGTH_SHORT).show();

                String content1 = "";
                content1 += "ID :" + response.body().getId() + "\n";
                content1 += "Employee Name :" + response.body().getEmployee_name() + "\n";
                content1 += "Employee Salary :" + response.body().getEmployee_salary() + "\n";
                content1 += "Employee Age :" + response.body().getEmployee_age() + "\n";

                txtsearchbyid.setText(content1);

            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
