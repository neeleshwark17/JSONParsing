package com.assignment.jsonparsing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String strArray[] = {};
        try {
            String jsonString = getJSONFile();
//            Log.e("onCreate: ", jsonString);
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonArray = jsonObject.getJSONArray("yoyo"); //yoyo is the key

            strArray = new String[jsonArray.length()];
            for (int i = 0; i < strArray.length; i++) {
                JSONObject json = jsonArray.getJSONObject(i);
                strArray[i] = json.getString("name"); //name is the key
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Toast.makeText(this, "thanks for using JSON", Toast.LENGTH_SHORT).show();
        }

        mListView = findViewById(R.id._listView);
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strArray);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.row, strArray);

        if (mListView != null) {
            mListView.setAdapter(arrayAdapter);
        }
    }


    //Function to read json file from Raw folder
    private String getJSONFile() throws IOException {
        String jsonString;
        InputStream inputStream = null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = getResources().openRawResource(R.raw.sample);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            while ((jsonString = bufferedReader.readLine()) != null) {
                stringBuilder.append(jsonString);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(stringBuilder);
    }


    //////JSON ---------------------------


    //Datatypes
/*
        Object {}
        Array []
        Number 234
        String "hi"
        Boolean true/false
        null null

 */


}



