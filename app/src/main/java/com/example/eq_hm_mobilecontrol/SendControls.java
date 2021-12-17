package com.example.eq_hm_mobilecontrol;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

/*public class AsyncTaskClass extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // This starts the AsyncTask
        // Doesn't need to be in onCreate()
        new SendControls().execute("my string parameter");
    }
}*/

public class SendControls extends AsyncTask<String, Integer, String> {

    // Here is the AsyncTask class:
    //
    // AsyncTask<Params, Progress, Result>.
    //    Params – the type (Object/primitive) you pass to the AsyncTask from .execute()
    //    Progress – the type that gets passed to onProgressUpdate()
    //    Result – the type returns from doInBackground()
    // Any of them can be String, Integer, Void, etc.

    int server_port = 54000;
    String host_ip_address = "192.168.1.16";

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    // Runs in UI before background thread is called
    /*@Override
    protected void onPreExecute() {
        super.onPreExecute();

        // Do something like display a progress bar
    }*/

    // This is run in a background thread
    @Override
    protected String doInBackground(String... params) {

        String message = params[0];
        try
        {
            s = new Socket(host_ip_address, server_port);
            pw = new PrintWriter(s.getOutputStream());
            pw.write(message);
            pw.flush();
            pw.close();
            s.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        // get the string from params, which is an array
        /*String myString = params[0];

        // Do something that takes a long time, for example:
        for (int i = 0; i <= 100; i++) {

            // Do things

            // Call this to update your progress
            publishProgress(i);
        }*/

        return "this string is passed to onPostExecute";
    }

    // This is called from background thread but runs in UI
    /*@Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        // Do things like update the progress bar
    }*/

    // This runs in UI when background thread finishes
    /*@Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        // Do things like hide the progress bar or change a TextView
    }*/
}