package com.dot.modulotech;

import android.app.Application;
import android.util.Log;

import com.dot.modulotech.utils.SessionManager;

import java.net.MalformedURLException;
import java.net.URL;

public class ModuloApplication extends Application {
    private static final String TAG = ModuloApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();

        SessionManager.init(getApplicationContext());
    }
}
