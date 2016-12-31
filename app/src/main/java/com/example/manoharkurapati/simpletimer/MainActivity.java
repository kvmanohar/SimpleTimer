package com.example.manoharkurapati.simpletimer;

import android.app.Activity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private CountDownTimer countDownTimer;

    private TextView textView;
    private Button btnStart;
    private Button btnReset;
    private ProgressBar mProgressBar;
    private Uri notification;
    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnStart = (Button) findViewById(R.id.btnStart);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this,notification);

        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                textView.setText("" + l / 1000);
                mProgressBar.incrementProgressBy(1);
            }

            @Override
            public void onFinish() {
                textView.setText("TIME-UP!!");
                mProgressBar.setProgress(5);
                ringtone.play();
            }

        };



        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countDownTimer.start();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                countDownTimer.cancel();
                textView.setText("5");
                mProgressBar.setProgress(0);
                ringtone.stop();
            }
        });

    }
}
