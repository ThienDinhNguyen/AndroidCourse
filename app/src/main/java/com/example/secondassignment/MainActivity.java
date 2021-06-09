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
        startActivity(getIntent());


        //Hooks
        bton = findViewById(R.id.firstButton);
        mEditUsername = findViewById(R.id.editUsername);
        mEditName = findViewById(R.id.editName);
        mEditEmail = findViewById(R.id.editEmail);
        dateText = findViewById(R.id.your_birthday);
        datePicker = findViewById(R.id.agePicker);
        valText = findViewById(R.id.textView5);

        int curYear, curMonth, curDay;
        curYear = c.getInstance().get(c.YEAR);
        curMonth = c.getInstance().get(c.MONTH);
        curDay = c.getInstance().get(c.DATE);

        //Button to go to 2nd activity
        bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (validateAge() && validate() && validateEmail()) {
                    valText.setText("Welcome");
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    st = mEditUsername.getText().toString();
                    intent.putExtra("Value", st);
                    startActivity(intent);

                    mEditUsername.setText("");
                    mEditName.setText("");
                    mEditEmail.setText("");
                    datePicker.init(curYear, curMonth, curDay, null);
                    valText.setText("");


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

            day = savedInstanceState.getInt("Day");
            month = savedInstanceState.getInt("Month");
            year = savedInstanceState.getInt("Year");
            datePicker.init(year, month, day, null);
        }


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Name", mEditUsername.getText().toString());
        outState.putString("Email", mEditEmail.getText().toString());
        outState.putString("Username", mEditUsername.getText().toString());

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        outState.putInt("Year", year);
        outState.putInt("Month", month + 1);
        outState.putInt("Date", day);
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
        if (userName.length() > 0 && name.length() > 0 ) {
            return true;
        } else {
            return false;
        }

    }

    public boolean validateEmail() {
        String email = mEditEmail.getText().toString();
        if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        return false;
    }

}