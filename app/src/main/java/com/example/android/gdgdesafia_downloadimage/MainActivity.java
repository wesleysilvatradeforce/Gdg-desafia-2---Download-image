package com.example.android.gdgdesafia_downloadimage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.android.gdgdesafia_downloadimage.RequestHttp.DownloadImage;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ImageView imageView = new ImageView(this);
        setContentView(imageView);
        
        String url = "https://cnet4.cbsistatic.com/hub/i/2011/10/27/a66dfbb7-fdc7-11e2-8c7c-d4ae52e62bcc/android-wallpaper5_2560x1600_1.jpg";
        DownloadImage imageDownload = new DownloadImage(
                imageView,
                url,
                this
        );
        imageDownload.setPlaceholder(R.mipmap.ic_launcher);

        imageDownload.execute();
    }
}
