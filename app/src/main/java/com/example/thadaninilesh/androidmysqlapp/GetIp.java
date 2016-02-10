package com.example.thadaninilesh.androidmysqlapp;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by thadaninilesh on 09-02-2016.
 */
public class GetIp extends Activity{

    public String getIp(){
        return getResources().getString(R.string.ip_address);
    }
}
