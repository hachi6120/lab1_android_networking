package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Bai1 extends AppCompatActivity {

    Button btn;
    TextView tv;
    ImageView img;

    Bitmap bitmap = null;

    String url = "https://th.bing.com/th/id/R.145e8621363ba956ec3c3909aa15340d?rik=SBjWJNrdjdICYA&riu=http%3a%2f%2fthuthuatphanmem.vn%2fuploads%2f2018%2f04%2f10%2fhinh-nen-thung-lung-nui-doi-dep_052339827.jpg&ehk=FT1q8tgpy3AkEhHO4u1S9Iaui7ukPaE%2flbeOn3h%2bPQE%3d&risl=&pid=ImgRaw&r=0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        btn = findViewById(R.id.bai1_btnLoadImg);
        tv = findViewById(R.id.bai1_tvMessage);
        img = findViewById(R.id.bai1_imgLoadImg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        bitmap = loadImage(url);
                        img.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText("Đã tải xong ảnh");
                                img.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
                myThread.start();
            }
        });
    }

    private Bitmap loadImage(String link){
        try {
            URL url = new URL(link);
            bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return bitmap;
    }
}