package com.example.bai11;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnload;
    private TextView tvload;
    private ImageView imload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnload= findViewById(R.id.button);
        tvload =findViewById(R.id.textView);
        imload =findViewById(R.id.imageView);


        btnload.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap= loadIMG("https://images.theconversation.com/files/177834/original/file-20170712-14488-19lw3sc.jpg?ixlib=rb-1.1.0&q=45&auto=format&w=926&fit=clip");
                imload.post(new Runnable() {
                    @Override
                    public void run() {
                        tvload.setText("thanh cong");
                        imload.setImageBitmap(bitmap);
                    }
                });
            }
        });
        thread.start();
    }
    //ham load anh tu mang
    private Bitmap loadIMG(String link){
        URL url;
        Bitmap bitmap=null;
        try {
            url = new URL(link);
            bitmap= BitmapFactory.decodeStream(url.openConnection().getInputStream());

        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return bitmap;

    }
}