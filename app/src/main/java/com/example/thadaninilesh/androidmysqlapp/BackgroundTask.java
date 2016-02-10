package com.example.thadaninilesh.androidmysqlapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by thadaninilesh on 08-02-2016.
 */
public class BackgroundTask extends AsyncTask<String,Void,String> { //here string,void,void corresponds to arguments of doInBackground,onProgressUpdate,onPostExecute method respectively.
    Context ctx;
    AlertDialog alertDialog;

    BackgroundTask(Context ctx){
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute(){
        alertDialog = new AlertDialog.Builder(ctx).create();
        alertDialog.setTitle("Login Information");

    }

    @Override
    protected String doInBackground(String... params){

        String ip = ctx.getResources().getString(R.string.ip_address);
        String reg_url = "http://"+ip+"/androidwebapp/register.php";
        String login_url = "http://"+ip+"/androidwebapp/login.php";
        String method = params[0];
        if(method.equals("Register")){
            String name = params[1];
            String user_name = params[2];
            String user_pass = params[3];
            try {
                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream os = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
                String data = URLEncoder.encode("name","UTF-8") +"="+URLEncoder.encode(name,"UTF-8")+"&"+
                        URLEncoder.encode("user_name","UTF-8") +"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8") +"="+URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                os.close();
                InputStream is = httpURLConnection.getInputStream();
                is.close();
                return "Register successful";
            }
            catch(Exception e){

            }
        }
        else if(method.equals("Login")){
            String user_name = params[1];
            String user_pass = params[2];
            try{
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
                String data = URLEncoder.encode("user_name","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"+
                        URLEncoder.encode("user_pass","UTF-8")+"="+URLEncoder.encode(user_pass,"UTF-8");
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"ISO-8859-1"));
                String response = "";
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    response+=line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            }
            catch(Exception e){}
        }
        return null;
    }
    @Override
    protected void onProgressUpdate(Void... values){
        super.onProgressUpdate(values);
    }
    @Override
    protected void onPostExecute(String result) {
        if(result.equals("Register successful")){
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }
        else if(result.equals("Login successful")){
            ctx.startActivity(new Intent(ctx, Contact.class));
            //alertDialog.setMessage(result);
            //alertDialog.show();
        }
        else if(result.equals("Data Invalid")){
            Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(ctx,"Not a valid selection",Toast.LENGTH_LONG).show();
        }

    }
}
