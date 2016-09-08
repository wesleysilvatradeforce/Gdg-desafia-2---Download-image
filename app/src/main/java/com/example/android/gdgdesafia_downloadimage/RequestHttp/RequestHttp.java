package com.example.android.gdgdesafia_downloadimage.RequestHttp;

import java.io.InputStream;

/**
 * Created by wesleysilva on 9/7/16.
 */

public interface RequestHttp {
    void onSuccess(InputStream inputStream);
    void error(Throwable error);
}
