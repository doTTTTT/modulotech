package com.dot.modulotech.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class SessionManager {
    private static final String NAME_SHARED = "modulo_tech";

    private static final String PREF_IS_LOGGED = "pref_is_logged";

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static SessionManager sessionManager = null;

    private SessionManager(Context context) {
        sharedPreferences = context.getSharedPreferences(NAME_SHARED, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public static void init(Context context) {
        sessionManager = new SessionManager(context);
    }

    public static SessionManager getInstance(){
        return sessionManager;
    }

    public void loginIn() {
        editor.putBoolean(PREF_IS_LOGGED, true).commit();
    }

    public void loginOut() {
        editor.putBoolean(PREF_IS_LOGGED, false).commit();
    }

    public boolean isLogged() {
        return sharedPreferences.getBoolean(PREF_IS_LOGGED, false);
    }
}