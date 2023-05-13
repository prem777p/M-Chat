package com.mahere.m_chat.Activities.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.mahere.m_chat.Activities.Home.Home;
import com.mahere.m_chat.R;
import com.mahere.m_chat.Utilities.SessionManager;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        hold();
    }

    private void hold(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);


                    boolean loginCheck = new SessionManager(getBaseContext()).checkSession();

                    if ( loginCheck ) {
                        startActivity(new Intent(getBaseContext(), Home.class));
                    }
                    else {
                        startActivity(new Intent(getBaseContext(), Login.class));
                    }


                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}