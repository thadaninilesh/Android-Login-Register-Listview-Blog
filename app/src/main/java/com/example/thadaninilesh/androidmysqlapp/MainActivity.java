package com.example.thadaninilesh.androidmysqlapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText ET_user_name, ET_user_pass;
    String user_name, user_pass;
    Button B1,B2;
    TextView message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ET_user_name = (EditText)findViewById(R.id.user_name);
        ET_user_pass = (EditText)findViewById(R.id.user_pass);
        B1 = (Button)findViewById(R.id.b1);
        B2 = (Button)findViewById(R.id.b2);
        message = (TextView)findViewById(R.id.message);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if(!(networkInfo!=null && networkInfo.isConnected())){
            B1.setEnabled(false);
            B2.setEnabled(false);
            message.setText("Network connetion not available");
        }
    }
    public void userReg(View view){
        startActivity(new Intent(this, Register.class));
    }

    public void userLogin(View view){
        user_name = ET_user_name.getText().toString();
        user_pass = ET_user_pass.getText().toString();
        String method = "Login";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, user_name, user_pass);


    }
}
