package com.vineetnet.stockprediction;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.*;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.androidplot.util.PixelUtils;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import com.softmoore.android.graphlib.Point;
import com.vinx.vpline.vpLine;

import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;
import java.util.*;

import java.lang.Math;

public class OneSimpleXYPlotActivity extends Activity {


    String cu="";
    int days=0;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_simple_xy_plot_example);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cu = extras.getString("res");
            days=getIntent().getIntExtra("days",days);

        }



        // create a couple arrays of y-values to plot:
        final float[] xval = new float[days];
        //Number[] series1Numbers = {1, 4, 2, 8, 4, 16, 8, 32, 16, 64};
        float[] yval = new float[days];

        String []dy= cu.split(",");

        for(int i=0;i<dy.length;i++){
            xval[i]=i+1;
            String ix=dy[i].replaceAll("[^\\d(.)]","");
            yval[i]=Float.parseFloat(ix);
            Log.d("lminx",Float.toString(yval[i]));
        }


        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int h = displayMetrics.heightPixels;
        int w = displayMetrics.widthPixels;
        Log.d("slpik",Integer.toString(h)+"______"+Integer.toString(w));

        vpLine vp  = (vpLine) findViewById(R.id.lineview);
        vpLine.setGraphAttrib(h, w,0.3f,0.05f,0.85f,0.6f, Color.BLACK,10,true);
        vpLine.setXY(xval,yval,2, Color.BLUE,10, 0.8f, 0.9f);
        vpLine.setPointLooks(true, Color.GREEN,25.0f,7);
        vpLine.setLabels(true,true,xval,yval,100,300,50,50,3,3, true, "","",Color.BLACK,Color.RED,10);
        vp.draw();

    }



    public float minValue(float[] array){
        List<Float> list = new ArrayList<Float>();
        for (float v : array) {
            list.add(v);
        }
        return Collections.min(list);
    }

    public float maxValue(float[] array){
        List<Float> list = new ArrayList<Float>();
        for (float v : array) {
            list.add(v);
        }
        return Collections.max(list);
    }

    public float diffValue(float[] array){
        List<Float> list = new ArrayList<Float>();
        for (float v : array) {
            list.add(v);
        }
        if(array.length>1)
            return Collections.max(list)-Collections.min(list);
        else
            return list.get(0);
    }
}