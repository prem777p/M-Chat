package com.mahere.m_chat.Utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SessionManager {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private final String KEY_IF_LOGGED_IN = "false";
    private final String PRE_FILE_NAME = "Mchat";
    private final int PRIVATE_MODE = 0;


    // constructor for session management
    public SessionManager(Context context){

        this.context = context;

        sharedPreferences = context.getSharedPreferences(PRE_FILE_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    //    first check data is exist or not
    public boolean checkSession()
    {
        return sharedPreferences.contains(KEY_IF_LOGGED_IN);
    }

    //    create or store value in share preference
    public void localStoreLoginDetail(String phoneNo)
    {
        String KEY_PHONE_NO = "key_session_phoneNo";
        editor.putString(KEY_PHONE_NO,phoneNo);
        editor.putBoolean(KEY_IF_LOGGED_IN, true);

        editor.commit(); // store in share preference
    }

    //    for logout to delete all data from share preference
    public void logoutSession()
    {
        editor.clear();
        editor.commit();

//        goes to login activity
        context.startActivity(new Intent(context, Long.class));
    }
}

