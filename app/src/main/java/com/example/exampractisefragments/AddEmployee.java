package com.example.exampractisefragments;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import database.DBHandler;

public class AddEmployee extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {


    EditText editTextEmpName , editTextPhone , editTextEmail;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonAdd;
    DBHandler dh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
        Intent i = getIntent();

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(this);

        editTextEmail = (EditText)findViewById(R.id.editTextAddEmployeeEmail);
        editTextEmpName = (EditText)findViewById(R.id.editTextAddEmployeeName);
        editTextPhone = (EditText)findViewById(R.id.editTextAddEmployeePhoneNumber);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroupAddEmployee);


        buttonAdd = (Button)findViewById(R.id.buttonAEaddEmployee);
        dh = new DBHandler(getApplicationContext());
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int checkedId = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(checkedId);

                boolean inserted = dh.addEmployee(editTextEmpName.getText().toString().trim(),Integer.parseInt(editTextPhone.getText().toString()),editTextEmail.getText().toString().trim(),radioButton.getText().toString(),spinner.getSelectedItem().toString());
                if(inserted){
                    Toast.makeText(AddEmployee.this, "inserted :"+spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Toast.makeText(AddEmployee.this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(AddEmployee.this,"select some thing", Toast.LENGTH_SHORT).show();
    }
}
