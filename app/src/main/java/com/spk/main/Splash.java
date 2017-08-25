package com.spk.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.spk.drawer.MainActivity;
import com.spk.utils.PreferencesHelper;

public class Splash extends AppCompatActivity {
    PreferencesHelper ph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ph = new PreferencesHelper(Splash.this);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    if (ph.getPreferences("status_login").equals("0")
                            || ph.getPreferences("status_login").equals("") && ph.getPreferences("status_login_admin").equals("0") || ph.getPreferences("status_login_admin").equals("")) {
                        Intent utama = new Intent(Splash.this, Login.class);
                        startActivity(utama);
                    } else if (ph.getPreferences("status_login").equals("1")) {
                        Intent utama = new Intent(Splash.this,
                                MainActivity.class);
                        startActivity(utama);
                    } else if (ph.getPreferences("status_login_admin").equals("1")) {
                        Intent utama = new Intent(Splash.this,
                                DestinasiWisataAdmin.class);
                        startActivity(utama);
                    }
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }
}
