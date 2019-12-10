package com.letstravel.androidapiclass.interfaceemployee;

import com.letstravel.androidapiclass.model.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeAPI {

    @GET("employees")
    Call<List<Employee>> getAllEmployees();
}