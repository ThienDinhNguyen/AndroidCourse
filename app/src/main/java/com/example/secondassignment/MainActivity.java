package com.example.secondassignment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Button bton;
    EditText mEditUsername, mEditName, mEditEmail;
    String st;
    TextView dateText;
    DatePicker datePicker;
    TextView valText;
    Calendar c;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        bton = findViewById(R.id.firstButton);
        mEditUsername = findViewById(R.id.editUsername);
        mEditName = findViewById(R.id.editName);
        mEditEmail = findViewById(R.id.editEmail);
        dateText = findViewById(R.id.your_birthday);
        datePicker = findViewById(R.id.agePicker);
        valText = findViewById(R.id.textView5);


        //Button to go to 2nd activity
        bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateAge() && validate()) {
                    valText.setText("Welcome");
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    st = mEditUsername.getText().toString();
                    intent.putExtra("Value", st);
                    startActivity(intent);
                } else {
                    valText.setText("Error");
                    bton.setEnabled(false);
                    bton.setEnabled(true);
                }
            }
        });
        int day, month, year;
        if (savedInstanceState != null) {
            mEditEmail.setText(savedInstanceState.getString("Email"));
            mEditName.setText(savedInstanceState.getString("Name"));
            mEditUsername.setText(savedInstanceState.getString("Username"));
            day = Integer.parseInt(savedInstanceState.getString("Day"));
            month = Integer.parseInt(savedInstanceState.getString("Month"));
            year = Integer.parseInt(savedInstanceState.getString("Year"));
            datePicker.init(year, month, day, null);
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Name", mEditUsername.getText().toString());
        outState.putString("Email", mEditEmail.getText().toString());
        outState.putString("Username", mEditUsername.getText().toString());

        String day = String.valueOf(datePicker.getDayOfMonth());
        String month = String.valueOf(datePicker.getMonth());
        String year = String.valueOf(datePicker.getYear());

        outState.putString("Year", year);
        outState.putString("Month", month);
        outState.putString("Date", day);
    }


    @SuppressLint("SetTextI18n")
    public boolean validateAge() {
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = curYear - userAge;

        if (isAgeValid < 18) {
            return false;
        } else {
            return true;
        }
    }

    //validate username, name and email
    public boolean validate() {
        String userName = mEditUsername.getText().toString();
        String name = mEditName.getText().toString();
        String email = mEditEmail.getText().toString();
        if (userName.length() > 0 && name.length() > 0 && email.length() > 0) {
            return true;
        } else {
            return false;
        }

    }

}