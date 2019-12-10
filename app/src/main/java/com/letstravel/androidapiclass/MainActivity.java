package com.letstravel.androidapiclass;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.letstravel.androidapiclass.interfaceemployee.EmployeeAPI;
import com.letstravel.androidapiclass.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView txtshowemployeedata;

   // private static String base_url = ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtshowemployeedata = findViewById(R.id.txtviewemployeedata);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://dummy.restapiexample.com/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //interface instance

        EmployeeAPI employeeAPI = retrofit.create(EmployeeAPI.class);
        Call<List<Employee>> listCall = employeeAPI.getAllEmployees();

        //Asynchrous call
        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()) {
                    //Toast.makeText(MainActivity.this, "Error" + response.code(), Toast.LENGTH_SHORT).show();

                    txtshowemployeedata.setText("code :" + response.code());
                    return;
                }
                List<Employee> employeeList = response.body();
                for (Employee employee : employeeList) {
                    String content = "";
                    content += "ID :" + employee.getId() + "\n";
                    content += "Employee Name :" + employee.getEmployee_name() + "\n";
                    content += "Employee Salary :" + employee.getEmployee_salary() + "\n";
                    content += "Employee Age :" + employee.getEmployee_age() + "\n";

                    txtshowemployeedata.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
               Toast.makeText(MainActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Hamro Error", "Error" +t.getLocalizedMessage());
            }
        });


    }
}
