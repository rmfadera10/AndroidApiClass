package com.letstravel.androidapiclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DashboardActivity extends AppCompatActivity {

    private Button btnsearchall, btnsearchbyid, btnaddemployee, btnupdatedelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        btnsearchall=findViewById(R.id.btnsearchall);
        btnsearchbyid=findViewById(R.id.btnsearchabyiddd);
        btnaddemployee=findViewById(R.id.btnadd);
        btnupdatedelete=findViewById(R.id.btnupdatedelete);

        btnsearchall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnsearchbyid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        btnaddemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, RegisterEmployee.class);
                startActivity(intent);
            }
        });

        btnupdatedelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DashboardActivity.this, UpdateDelete.class);
                startActivity(intent);
            }
        });
    }
}
