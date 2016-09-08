package com.example.android.gdgdesafia_downloadimage;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by wesleysilva on 9/7/16.
 */

public class DownloadImage extends ImageView {

    public DownloadImage(Context context) {
        super(context);
    }

    public DownloadImage(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DownloadImage(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public DownloadImage(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }



}
