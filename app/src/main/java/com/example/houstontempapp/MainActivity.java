package com.example.houstontempapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.URL;


public class MainActivity extends AppCompatActivity implements TempTaskResponse {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonPressed(View view) {
        try {
            URL url = new URL("https://api.openweathermap.org/data/2.5/weather?q=Houston&units=metric&APPID=2f0d0592821e12ab4234287281b53996");
            FindTempTask task = new FindTempTask();
            task.delegate = this;
            task.execute(url);

            Button backButton = findViewById(R.id.button2);
            backButton.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            Log.d("urlException", Log.getStackTraceString(e));
        }
    }

    public void backButtonPressed(View view) {
        TextView textView = findViewById(R.id.textView);
        textView.setText("");

        TextView celsiusTextView = findViewById(R.id.textView3);
        celsiusTextView.setText("");

        Button backButton = findViewById(R.id.button2);
        backButton.setVisibility(View.GONE);
    }

    public void processFinish(String output) {
        TextView textView = findViewById(R.id.textView);
        textView.setText(output);

        //Log.d("houstonTemp", output);

        TextView celsiusTextView = findViewById(R.id.textView3);
        celsiusTextView.setText("C");
    }
}
