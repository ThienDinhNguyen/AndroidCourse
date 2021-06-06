package com.example.secondassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity  {

    Button bton;
    EditText mEditUsername;
    String st;
    TextView dateText;
    DatePicker datePicker;
    TextView ageValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hooks
        bton = findViewById(R.id.firstButton);
        mEditUsername = findViewById(R.id.editUsername);
        dateText = findViewById(R.id.your_birthday);
        datePicker = findViewById(R.id.agePicker);

        //Button to go to 2nd activity
        bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateAge()){

                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    st = mEditUsername.getText().toString();
                    intent.putExtra("Value", st);
                    startActivity(intent);
                    finish();
                }
                else{
                    bton.setEnabled(false);
                    bton.setEnabled(true);
                }
            }
        });



    }

    @SuppressLint("SetTextI18n")
    public boolean validateAge(){
        int curYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = curYear - userAge;

        if (isAgeValid<18){

            ageValid = findViewById(R.id.textView5);
            ageValid.setText("You are not eligible to register");
            return false;
        }
        else{
            return true;
        }
    }

}