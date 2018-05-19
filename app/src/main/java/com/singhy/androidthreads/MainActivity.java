package com.singhy.androidthreads;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private static final String TAG = "MainActivity";

    private Button buttonStart, buttonStop;
    int count = 0;
    private boolean mStopLoop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "Thread id of Main thread: " + Thread.currentThread().getId());

        buttonStart = (Button) findViewById(R.id.buttonThreadStarter);
        buttonStop = (Button) findViewById(R.id.buttonStopthread);

        buttonStart.setOnClickListener(this);
        buttonStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonThreadStarter:
                mStopLoop = false;

                //creating a new thread and running loop inside it
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (!mStopLoop) {
                            try {
                                Thread.sleep(1000);
                                count++;
                            } catch (InterruptedException e) {
                                Log.i(TAG, e.getMessage());
                            }
                            Log.i(TAG, "Thread id in while loop: " + Thread.currentThread().getId() + ", Count : " + count);
                        }
                    }
                }).start();

                break;

            case R.id.buttonStopthread:
                mStopLoop = true;
                break;

            default:
                break;
        }
    }

}
