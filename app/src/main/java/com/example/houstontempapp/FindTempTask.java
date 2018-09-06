package com.example.houstontempapp;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

class FindTempTask extends AsyncTask<URL, Void, String> {

    TempTaskResponse delegate = null;

    @Override
    protected String doInBackground(URL... urls) {
        String houstonTemp = "";
        try {
            URLConnection request = urls[0].openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject rootObj = root.getAsJsonObject();
            houstonTemp = rootObj.getAsJsonObject("main").get("temp").getAsString();
        } catch (Exception e) {
            Log.d("buttonPressException", Log.getStackTraceString(e));
        }
        return houstonTemp;
    }

    @Override
    protected void onPostExecute(String result) {
        delegate.processFinish(result);
    }
}
