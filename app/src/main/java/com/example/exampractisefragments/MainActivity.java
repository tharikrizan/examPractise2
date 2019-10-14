package com.example.exampractisefragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button buttonAddEmployee , buttonEditEmployee , buttonSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAddEmployee = (Button)findViewById(R.id.buttonMainAddEmployee);
        buttonEditEmployee = (Button)findViewById(R.id.buttonMainEditEmployee);
        buttonSearch = (Button)findViewById(R.id.buttonMainSearch);


        buttonAddEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),AddEmployee.class);

                startActivity(i);
            }
        });
        buttonEditEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),EditEmployee.class);

                startActivity(i);
            }
        });
        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),ViewEmployee.class);

                startActivity(i);
            }
        });


    }
}
