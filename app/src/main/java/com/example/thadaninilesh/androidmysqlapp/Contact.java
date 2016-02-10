package com.example.thadaninilesh.androidmysqlapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class Contact extends AppCompatActivity {
    Button B1,B2;
    TextView textView;
    String jsonString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        B1 = (Button)findViewById(R.id.b1);
        B2 = (Button)findViewById(R.id.b2);
        textView = (TextView)findViewById(R.id.textView);
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnected()){
        //if(true){
            textView.setVisibility(View.INVISIBLE);
        }
        else{
            B1.setEnabled(false);
            B2.setEnabled(false);
        }
    }

    public void addContact(View view){
        startActivity(new Intent(this, AddInfo.class));
    }

    public void viewContact(View view){
        GetJsonData getJsonData = new GetJsonData();
        getJsonData.execute();
    }

    class GetJsonData extends AsyncTask<Void, Void, String>{
        String json_url;
        String JSON_STRING;
        @Override
        protected void onPreExecute() {
            String ip = getResources().getString(R.string.ip_address);
            json_url = "http://"+ip+"/androidwebapp/json_get_data.php";
        }

        @Override
        protected String doInBackground(Void... params) {
            try {
                URL url = new URL(json_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                StringBuilder stringBuilder = new StringBuilder();
                while((JSON_STRING = bufferedReader.readLine())!=null){
                    stringBuilder.append(JSON_STRING+"\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }
            catch(Exception e){}

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            TextView jsonData = (TextView)findViewById(R.id.jsonData);
            jsonData.setText(result);
            jsonString = result;

        }
    }

    public void parseJSON(View view){
        if(jsonString==null){
            Toast.makeText(getApplicationContext(),"First Get JSON",Toast.LENGTH_LONG).show();
        }
        else{
            Intent intent = new Intent(this,DisplayListView.class);
            intent.putExtra("json_data",jsonString); //any other variable that are to be passed to another activity
            startActivity(intent);
        }

    }
}
