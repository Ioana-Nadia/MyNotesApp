package com.example.newnotesapp;

import android.content.Context;
import android.os.StrictMode;
import android.util.Log;
import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnection {
    Connection con;
    public Connection classConnection(Context context) {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        String ConnectionURL = null;
        try {
            Class.forName(context.getString(R.string.driver_class));
            ConnectionURL = context.getString(R.string.url);
            con = DriverManager.getConnection(ConnectionURL);
        }
        catch(Exception e){
            Log.e("The error is: ", e.getMessage());
        }
        return con;
    }
}