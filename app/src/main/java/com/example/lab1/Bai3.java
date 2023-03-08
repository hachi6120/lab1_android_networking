package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Bai3 extends AppCompatActivity implements Listener{

    Button btn;
    TextView tv;
    ImageView img;

    String url = "https://th.bing.com/th/id/R.145e8621363ba956ec3c3909aa15340d?rik=SBjWJNrdjdICYA&riu=http%3a%2f%2fthuthuatphanmem.vn%2fuploads%2f2018%2f04%2f10%2fhinh-nen-thung-lung-nui-doi-dep_052339827.jpg&ehk=FT1q8tgpy3AkEhHO4u1S9Iaui7ukPaE%2flbeOn3h%2bPQE%3d&risl=&pid=ImgRaw&r=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);

        btn = findViewById(R.id.bai3_btnLoadImg);
        tv = findViewById(R.id.bai3_tvMessage);
        img = findViewById(R.id.bai3_imgLoadImg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new LoadImageTask(Bai3.this,Bai3.this).execute(url);
            }
        });
    }


    @Override
    public void onImageLoaded(Bitmap bitmap) {
        img.setImageBitmap(bitmap);
        tv.setText("Đã tải ảnh");
    }

    @Override
    public void onError() {
        tv.setText("Lỗi Tải Ảnh");
    }
}