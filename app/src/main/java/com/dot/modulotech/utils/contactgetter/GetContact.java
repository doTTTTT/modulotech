package com.dot.modulotech.utils.contactgetter;

import android.os.AsyncTask;
import android.support.v4.util.Pair;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

class GetContact extends AsyncTask<String, Void, Pair<Integer, String>> {
    private URL url;
    private ContactCallback callback;

    public GetContact(URL url, ContactCallback callback){
        this.url = url;
        this.callback = callback;
    }

    @Override
    protected Pair<Integer, String> doInBackground(String... params) {
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setReadTimeout(5000);
            http.setConnectTimeout(15000);
            http.setRequestProperty("Accept", "*");
            http.setUseCaches(false);

            http.connect();

            Integer code = http.getResponseCode();
            String inputStream = null;
            if (http.getResponseCode() >= 400) {
                inputStream = getResponse(http.getErrorStream());
            } else {
                inputStream = getResponse(http.getInputStream());
            }

            http.disconnect();

            return Pair.create(code, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Pair<Integer, String> https) {
        if (callback != null) {
            if (https.first >= 400) {
                callback.onError(https.second);
            } else {
                callback.onSucces(https.second);
            }
        }
    }

    public static String getResponse(InputStream in){
        InputStream result = new BufferedInputStream(in);
        StringBuilder response = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(result));

        try {
            String line = null;
            while ((line = reader.readLine()) != null){
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return response.toString();
        }
    }
}
