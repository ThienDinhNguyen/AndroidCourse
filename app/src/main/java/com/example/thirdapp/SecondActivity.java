package com.example.thirdapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class SecondActivity extends AppCompatActivity {
    TextView name,desc,occupation,age;
    String url,sName, sDesc, sOccup, sAge;
    ImageView iv;
    Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.name);
        desc = findViewById(R.id.desc);
        age = findViewById(R.id.age);
        occupation = findViewById(R.id.occupation);
        iv = findViewById(R.id.img_review);
        backButton = findViewById(R.id.returnButton);

        sName = getIntent().getExtras().getString("Name");
        sDesc = getIntent().getExtras().getString("Desc");
        sAge = getIntent().getExtras().getString("Age");
        sOccup = getIntent().getExtras().getString("Occu");

        name.setText(sName);
        desc.setText(sDesc);
        age.setText(sAge);
        occupation.setText(sOccup);

        url = getIntent().getExtras().getString("URL");
        Picasso.get().load(url).into(iv);

        backButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
