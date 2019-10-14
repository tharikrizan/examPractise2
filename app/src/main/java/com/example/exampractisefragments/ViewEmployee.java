package com.example.exampractisefragments;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;

import database.DBHandler;
import database.EmployeeClassInfo;

public class ViewEmployee extends AppCompatActivity {


    DBHandler dh;
    ListView listView;
    Button buttonSearch;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_employee);

        listView = (ListView)findViewById(R.id.listViewViewEmployee);

         spinner = (Spinner) findViewById(R.id.spinnerViewEmployee);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        dh = new DBHandler(getApplicationContext());

        buttonSearch = (Button)findViewById(R.id.buttonViewEmployeeSearch);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor c= dh.search(EmployeeClassInfo.EmployeeInfo.COLUMN_Type,spinner.getSelectedItem().toString());


                String[] projection = {
                        BaseColumns._ID,
                        EmployeeClassInfo.EmployeeInfo.COLUMN_NAME,
                        EmployeeClassInfo.EmployeeInfo.COLUMN_TELEPHONE,
                        EmployeeClassInfo.EmployeeInfo.COLUMN_EMAIL,

                        EmployeeClassInfo.EmployeeInfo.COLUMN_GENDER,

                        EmployeeClassInfo.EmployeeInfo.COLUMN_Type
                };
                int[] to = {R.id.textViewID,R.id.textViewName,R.id.textViewPhone,R.id.textViewEmail,R.id.textViewGender,R.id.textViewType};
                SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getApplicationContext(),R.layout.employee_item,c,projection,to,0);

                listView.setAdapter(simpleCursorAdapter);
            }
        });




    }
}
