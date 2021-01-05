package com.vineetnet.stockprediction;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.TextView;

public class MyTask extends AsyncTask<Void,Integer,String> {
    Context con;
    TextView tv;
    Button bt;
    ProgressDialog pd;
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 15000;
    public static final int CONNECTION_TIMEOUT = 15000;

    MyTask(Context c, TextView t, Button b){
        this.con=c;
        this.tv=t;
        this.bt=b;
    }


    @Override
    protected String doInBackground(Void... voids) {

        int i=0;
        synchronized(this)
        {
            while(i<=10) {
                try {
                    wait(1500);
                    i++;
                    publishProgress(i);
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }
            }
        }
        return "Complete.";
    }

    @Override
    protected void onPostExecute(String result) {
        tv.setText(result);
        pd.hide();
    }

    @Override
    protected void onPreExecute() {
        pd = new ProgressDialog(con);
        pd.setTitle("Download progress");
        pd.setProgress(0);
        pd.setMax(10);
        pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        pd.show();

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int pr = values[0];
        pd.setProgress(pr);
        tv.setText("In progress.. ");
    }
}
