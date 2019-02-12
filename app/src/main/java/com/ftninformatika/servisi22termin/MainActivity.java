package com.ftninformatika.servisi22termin;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MyBoundService.TextChanger {

    Button bStart;
    Button bStop;
    MyBoundService myBoundService;
    boolean bound = false;
    TextView textView;

    private int a = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        bStart = findViewById(R.id.bStart);
        bStop = findViewById(R.id.bStop);
        bStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startMyService();
            }
        });

        bStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                testService();
            }
        });

        bindToMyService();

    }


    private void testService() {
        int x = a % 3;
        myBoundService.getVoce(x);
        a++;
    }


    @Override
    protected void onStop() {
        super.onStop();

        if (bound) {
            myBoundService.setTextChanger(null);
            unbindService(mConnection);
            bound = false;
        }

    }

    private void startMyService() {

        Intent intent = new Intent(MainActivity.this, MyStartedService.class);
        intent.putExtra(MyStartedService.SECONDS_TAG,5);
        startService(intent);

    }

    private void bindToMyService() {

        Intent intent = new Intent(MainActivity.this,MyBoundService.class);
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);

    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder)service;
            myBoundService = binder.getService();
            myBoundService.setTextChanger(MainActivity.this);
            bound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            bound = false;

        }
    };

    @Override
    public void changeText(String text) {

        textView.setText(text);

    }
}
