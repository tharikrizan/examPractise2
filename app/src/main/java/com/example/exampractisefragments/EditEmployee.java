package com.example.exampractisefragments;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import database.DBHandler;
import database.EmployeeClassInfo;

public class EditEmployee extends AppCompatActivity {

    EditText editTextEmpName , editTextPhone , editTextEmail , editTextEmpNuumber;
    RadioGroup radioGroup;
    RadioButton radioButton;
    Button buttonEdit , buttonSearch;
    Spinner spinner;
    DBHandler dh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_employee);
        spinner = (Spinner) findViewById(R.id.spinner2);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        editTextEmail = (EditText)findViewById(R.id.editTextEditEmployeeEmail);
        editTextEmpName = (EditText)findViewById(R.id.editTextEditEmployeeName);
        editTextPhone = (EditText)findViewById(R.id.editTextEditEmployeePhone);
        editTextEmpNuumber = (EditText)findViewById(R.id.editTextEditEmployeeEmployeeNumber);


        radioGroup = (RadioGroup) findViewById(R.id.radioGroupEditEmployee);

        dh = new DBHandler(getApplicationContext());

        buttonEdit = (Button)findViewById(R.id.buttonEditEmployee);
        buttonSearch = (Button)findViewById(R.id.buttonEditEmployeeSearch);


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c = dh.search(EmployeeClassInfo.EmployeeInfo._ID,editTextEmpNuumber.getText().toString().trim());
                while (c.moveToNext()){
                    editTextEmail.setText(c.getString(c.getColumnIndexOrThrow(EmployeeClassInfo.EmployeeInfo.COLUMN_EMAIL)));

                    if(c.getString(c.getColumnIndexOrThrow(EmployeeClassInfo.EmployeeInfo.COLUMN_GENDER)).equals("Male")){
                        radioButton = (RadioButton)findViewById(R.id.radioButtonEditEmployeeMale);
                    }else{
                        radioButton = (RadioButton)findViewById(R.id.radioButtonEditEmployeeFemale);
                    }
                    radioButton.setChecked(true);

                    editTextEmpName.setText(c.getString(c.getColumnIndexOrThrow(EmployeeClassInfo.EmployeeInfo.COLUMN_NAME)));
                    editTextPhone.setText(c.getString(c.getColumnIndexOrThrow(EmployeeClassInfo.EmployeeInfo.COLUMN_TELEPHONE)));
                    if (c.getString(c.getColumnIndexOrThrow(EmployeeClassInfo.EmployeeInfo.COLUMN_Type)).equals("Permanant")  ){
                        spinner.setSelection(0);
                    }else{
                        spinner.setSelection(1);
                    }

                }
            }
        });
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean updated = dh.updateEmployee(editTextEmpNuumber.getText().toString().trim(),editTextEmpName.getText().toString().trim(),Integer.parseInt(editTextPhone.getText().toString()),editTextEmail.getText().toString().trim(),radioButton.getText().toString(),spinner.getSelectedItem().toString());
                if(updated){
                    Toast.makeText(EditEmployee.this, "Update Successfull", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(EditEmployee.this, "Update Not ok", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }
}
