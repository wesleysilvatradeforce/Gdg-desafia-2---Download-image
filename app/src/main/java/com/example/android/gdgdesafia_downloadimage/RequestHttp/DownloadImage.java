package com.example.android.gdgdesafia_downloadimage.RequestHttp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.ref.WeakReference;

/**
 * Created by wesleysilva on 9/7/16.
 */

public class DownloadImage implements RequestHttp {

    private WeakReference<ImageView> imageView;
    private Activity context;
    private Bitmap imagePlaceSuccess = null;
    private Response response;
    private Drawable imagePlaceHolder;
    private Drawable imagePlaceError;

    public DownloadImage(ImageView imageView, String url, Activity context) {
        this.imageView = new WeakReference<>(imageView);
        this.context = context;
        response = new Response(url);
        response.setListenner(this);
    }

    public void execute(){
        Drawable image = this.getImagePlaceHolder();

        if(image != null){
            try {
                this.setContentInImageView(image);
            } catch (Exception e) {
                this.error(e);
            }
        }
        response.execute();
    }

    private void setContentInImageView(Object image) throws Exception {
        ImageView imageView = this.imageView.get();

        imageView.animate()
                .alpha(.0f)
                .setDuration(150)
                .start();

        imageView.animate()
                .alpha(1.f)
                .setStartDelay(150)
                .setDuration(300)
                .setInterpolator(new BounceInterpolator())
                .start();

        if(image instanceof Drawable){
            imageView.setImageDrawable((Drawable) image);
        } else if( image instanceof Bitmap){
            imageView.setImageBitmap((Bitmap) image);
        }
        else{
            throw new Exception("Error type variable");
        }
    }

    private Drawable getImagePlaceHolder(){
        if(this.imagePlaceHolder == null){
            return null;
        }
        return this.imagePlaceHolder;
    }

    public void setPlaceholder(int id){
        this.imagePlaceHolder = ContextCompat.getDrawable(
                this.context,
                id
        );
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onSuccess(final InputStream inputStream) {
        imagePlaceSuccess = BitmapFactory.decodeStream(inputStream);
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    setContentInImageView(imagePlaceSuccess);
                } catch (Exception e) {
                    error(e);
                }
            }
        });
    }

    @Override
    public void error(Throwable error) {
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                imageView.get().setImageDrawable(
                        ContextCompat.getDrawable(context,android.R.mipmap.sym_def_app_icon)
                );
            }
        });
    }
}
