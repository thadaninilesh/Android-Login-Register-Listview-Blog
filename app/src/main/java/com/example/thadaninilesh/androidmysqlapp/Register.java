package com.example.thadaninilesh.androidmysqlapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Register extends Activity {
    EditText ET_name, ET_user_name, ET_user_pass;
    String name, user_name, user_pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ET_name = (EditText)findViewById(R.id.name);
        ET_user_name = (EditText)findViewById(R.id.new_user_name);
        ET_user_pass = (EditText)findViewById(R.id.new_user_pass);
    }

    public void userReg(View view){ //view arguements for all button method compulsory
        name = ET_name.getText().toString();
        user_name = ET_user_name.getText().toString();
        user_pass = ET_user_pass.getText().toString();
        String method = "Register";
        BackgroundTask backgroundTask = new BackgroundTask(this);
        backgroundTask.execute(method, name, user_name, user_pass);
        finish();
    }

    public void userLogin(View view){
        startActivity(new Intent(this, MainActivity.class));
    }


}
