package com.example.thirdapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText mEditName, mEditAge, mEditDesc, mEditOccupation, mURL;

    Button bton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditName = findViewById(R.id.editName);
        mEditAge = findViewById(R.id.editAge);
        mEditDesc = findViewById(R.id.editDesc);
        mEditOccupation = findViewById(R.id.editOccupation);
        mURL = findViewById(R.id.editURL);
        bton = findViewById(R.id.confirmButton);

        bton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validateFields()){
                    Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                    intent.putExtra("Name",mEditName.getText().toString());
                    intent.putExtra("Age",mEditAge.getText().toString());
                    intent.putExtra("Desc",mEditDesc.getText().toString());
                    intent.putExtra("URL",mURL.getText().toString());
                    intent.putExtra("Occu",mEditOccupation.getText().toString());



                    startActivity(intent);





                }
                else{
                    Toast.makeText(getApplicationContext(),"Fields cannot be blank",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    public boolean validateFields() {
        String sEditName = mEditName.getText().toString();
        String sEditAge = mEditAge.getText().toString();
        String sEditDesc = mEditDesc.getText().toString();
        String sEditOccupation = mEditOccupation.getText().toString();
        String sURL = mURL.getText().toString();
        
        if (sEditName.length() > 0 && sEditAge.length() > 0 && sEditDesc.length() > 0 && sEditOccupation.length() > 0 && sURL.length() > 0){
            return true;
        }
        return false;
    }


}