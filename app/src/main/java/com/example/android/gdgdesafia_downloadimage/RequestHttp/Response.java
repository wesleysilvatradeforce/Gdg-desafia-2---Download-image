package com.example.android.gdgdesafia_downloadimage.RequestHttp;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Response {

    private String urlAddress;
    private HttpURLConnection connection;
    private RequestHttp listenner;

    public void setListenner(RequestHttp callback) {
        this.listenner = callback;
    }

    public Response(String urlAddress) {
        this.urlAddress = urlAddress;
    }
    public void execute(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(listenner == null){
                        throw new Exception("Dont Listenner");
                    }

                    URL url = new URL(urlAddress);
                    connection = (HttpURLConnection) url.openConnection();
                    InputStream inputStream = connection.getInputStream();
                    listenner.onSuccess(inputStream);

                } catch (MalformedURLException e) {
                    listenner.error(e);
                    return;
                } catch (IOException e) {
                    listenner.error(e);
                    return;
                } catch (Exception e) {
                    listenner.error(e);
                    return;
                } finally {
                    connection.disconnect();
                    listenner=null;
                }
            }
        }).start();
    }

}
