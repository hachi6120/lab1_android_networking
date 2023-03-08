package com.example.lab1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    Button btn;
    TextView tv;
    ImageView img;

    ProgressDialog progressDialog;

    Bitmap bitmap = null;

    String url = "https://th.bing.com/th/id/R.145e8621363ba956ec3c3909aa15340d?rik=SBjWJNrdjdICYA&riu=http%3a%2f%2fthuthuatphanmem.vn%2fuploads%2f2018%2f04%2f10%2fhinh-nen-thung-lung-nui-doi-dep_052339827.jpg&ehk=FT1q8tgpy3AkEhHO4u1S9Iaui7ukPaE%2flbeOn3h%2bPQE%3d&risl=&pid=ImgRaw&r=0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = findViewById(R.id.btnLoadImg);
        tv = findViewById(R.id.tvMessage);
        img = findViewById(R.id.imgLoadImg);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*final Thread myThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final Bitmap bitmap = loadImage("");
                        img.post(new Runnable() {
                            @Override
                            public void run() {
                                tv.setText("Đã Tải Ảnh Thành Công");
                                img.setImageBitmap(bitmap);
                            }
                        });
                    }
                });
                myThread.start();*/

                progressDialog = ProgressDialog.show(MainActivity.this,"","Downloading...");
                Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        bitmap = loadImage(url);
                        Message msg = message.obtainMessage();
                        Bundle bundle = new Bundle();
                        String threadMessage = "Đã tải ảnh";
                        bundle.putString("message",threadMessage);
                        msg.setData(bundle);
                        message.sendMessage(msg);
                    }
                };
                Thread thread = new Thread(runnable);
                thread.start();
            }
        });
    }

    private Handler message = new Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            String message = bundle.getString("message");
            tv.setText(message);
            img.setImageBitmap(bitmap);
            progressDialog.dismiss();
        }
    };

    private Bitmap loadImage(String link){
        try {
            URL url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            Bitmap bitmap = new BitmapFactory().decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}