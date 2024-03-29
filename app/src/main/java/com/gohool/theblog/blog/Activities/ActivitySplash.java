package com.gohool.theblog.blog.Activities;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.gohool.theblog.blog.R;

public class ActivitySplash extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //menyembunyikan title bar di layar acitivy
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        /** Sets a layout for this activity */
        setContentView(R.layout.activity_splash);

        /** Creates a count down timer, which will be expired after 5000 milliseconds */
        new CountDownTimer(5000,1000) {

            /** This method will be invoked on finishing or expiring the timer */
            @Override
            public void onFinish() {
                /** Creates an intent to start new activity */
                Intent intent = new Intent(getBaseContext(), FirstActivity.class);

                //memulai activity baru ketika waktu timer habis
                startActivity(intent);

                //menutup layar activity
                finish();

            }

            /** This method will be invoked in every 1000 milli seconds until
             * this timer is expired.Because we specified 1000 as tick time
             * while creating this CountDownTimer
             */
            @Override
            public void onTick(long millisUntilFinished) {

            }
        }.start();

    }
}
