package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Bai4 extends AppCompatActivity {

    EditText edt;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai4);

        edt = findViewById(R.id.bai4_edt);
        btn = findViewById(R.id.bai4_btn);
        tv = findViewById(R.id.bai4_tv);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AsyncTaskRunner asyncTaskRunner = new AsyncTaskRunner(tv,edt,Bai4.this);
                String sleepTime = edt.getText().toString();
                asyncTaskRunner.execute(sleepTime);
            }
        });
    }
}