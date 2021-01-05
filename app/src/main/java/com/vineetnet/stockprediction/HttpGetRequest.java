package com.vineetnet.stockprediction;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HttpGetRequest extends AsyncTask<String, Integer, String> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 150000;
    public static final int CONNECTION_TIMEOUT = 150000;

    TextView tv;
    Context con;
//    ProgressDialog pd;
    Button bt2,bt;
    GraphView gvx;
    int daysx;
    LineGraphSeries<DataPoint> series;

    HttpGetRequest(Context c, TextView t, Button bt2, Button bt, GraphView gvx, int daysx){
        this.con=c;
        this.tv=t;
        //this.bt2=bt2;
        this.bt=bt;
        this.gvx=gvx;
        this.daysx=daysx;

    }
    @Override
    protected String doInBackground(String... params) {

//        bt2.setEnabled(false);
//        bt.setEnabled(false);
        String stringUrl = params[0];
        String result;
        String inputLine;
        int i=0;
        synchronized(this) {

            try {


                //Create a URL object holding our url
                URL myUrl = new URL(stringUrl);

                //Create a connection
                HttpURLConnection connection = (HttpURLConnection)
                myUrl.openConnection();

                //Set methods and timeouts
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setConnectTimeout(CONNECTION_TIMEOUT);

                //Connect to our url
                connection.connect();

                //Create a new InputStreamReader
                InputStreamReader streamReader = new InputStreamReader(connection.getInputStream());

                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();

                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null)
                {
                    stringBuilder.append(inputLine);
                }

                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();

                //Set our result equal to our stringBuilder
                result = stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
                result = null;
            }


        }

        return result;
    }
    @Override
    protected void onPostExecute(String result) {


        String []dy= result.split(",");
        ArrayList<Double> ls = new ArrayList<>();
        for(String i:dy){

            String ix=i.replaceAll("[^\\d(.)]","");
            ls.add(Double.parseDouble(ix));
        }
        Double []jx = ls.toArray(new Double[0]);
        for(Double j : jx){
            Log.d("juhytre",j.toString());
        }


        Intent ic = new Intent(con,OneSimpleXYPlotActivity.class);
        ic.putExtra("res",result);
        ic.putExtra("days",daysx);
        Log.d("kopis","gaya");
        con.startActivity(ic);
        //tv.setText(result);



    }


}