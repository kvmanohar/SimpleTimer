package com.example.manoharkurapati.simpletimer;

import android.app.Activity;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import org.w3c.dom.Text;

public class MainActivity extends Activity {

    private CountDownTimer countDownTimer;

    private TextView textView;
    private TextSwitcher textSwitcher;

    private Button btnStart;
    private Button btnReset;
    private ProgressBar mProgressBar;
    private Uri notificationUri;
    private Ringtone ringtone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textSwitcher = (TextSwitcher) findViewById(R.id.textSwitcher);

        btnReset = (Button) findViewById(R.id.btnReset);
        btnStart = (Button) findViewById(R.id.btnStart);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Get Alarm sound file Uri and set it to Ringtone ready to play
        notificationUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        ringtone = RingtoneManager.getRingtone(this,notificationUri);

        //Setup TextSwitcher
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory(){
            @Override
            public View makeView() {
                textView = new TextView(MainActivity.this);
                textView.setTextColor(Color.BLUE);
                textView.setTextSize(80);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                return textView;
            }
        });


        countDownTimer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                textSwitcher.setText("" + l/1000);
                mProgressBar.incrementProgressBy(1);
            }

            @Override
            public void onFinish() {
                textSwitcher.setText("TIME-UP!!");
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
                textSwitcher.setText("5");
                mProgressBar.setProgress(0);
                ringtone.stop();
            }
        });



    }
}
