package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    private int mCounterTracker = 0;

    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = findViewById(R.id.show_count);
        Log.d(LOG_TAG, "Hello World");
    }

    public void showToast(View view) {
        Log.d(LOG_TAG, "In showToast");
        Toast toast = Toast.makeText(this, R.string.toast_message, Toast.LENGTH_LONG);
        toast.show();
    }

    public void countUp(View view) {
        Log.d(LOG_TAG, "In countUp");
        ++mCounterTracker;
        if (mShowCount != null){
            mShowCount.setText(Integer.toString(mCounterTracker));
        }
    }
}
