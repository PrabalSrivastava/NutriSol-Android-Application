package com.example.ronitshrivastava.foodapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {
    private static final String TAG="mytest";
    private static final String recourseUrl="https://api.edamam.com/api/nutrition-data?app_id=3ebdac44&app_key=eb8bd0fd118a29471ae418d236f2e778&ingr=";
    private static final String encodedSpacebar="%20";
    Context context;
    public HttpHandler(/*Context context*/){
        //this.context=context;
    }
    public String makeServiceCall(String reqUrl)
    {
        String finalUrl=recourseUrl+reqUrl;
        String response=null;
        try
        {

            URL url=new URL(finalUrl);
            HttpURLConnection conn=(HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            InputStream in=new BufferedInputStream(conn.getInputStream());
            response=convertStreamToString(in);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            Log.e(TAG,"Exception:"+ e.getMessage());
            //Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Exception:"+ e.getMessage());
            //Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }catch (Exception err){
            Log.e(TAG,"Exception"+ err.getMessage());
            Toast.makeText(context,err.getMessage(),Toast.LENGTH_SHORT).show();
        }
        return response;
    }
    public String convertStreamToString(InputStream is)
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(is));
        StringBuilder sb =new StringBuilder();

        String line;
        try{
            while ((line=reader.readLine())!=null)
                sb.append(line).append("\n");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Exception"+ e.getMessage());
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }finally {
            try{
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(TAG,"Exception"+ e.getMessage());
                Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
        return sb.toString();
    }
}
