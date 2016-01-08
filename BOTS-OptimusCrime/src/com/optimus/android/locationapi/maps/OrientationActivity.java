package com.optimus.android.locationapi.maps;

import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class OrientationActivity extends Activity implements SensorEventListener{
	
	Sensor accelerometer;
	SensorManager sm;
	TextView acceleration;
	TextView iView;
	TextView timeView;
	double i;
	float sec;
	long begin;
	long time;
	long finalTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orientation);
        
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        accelerometer = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sm.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        
        acceleration = (TextView) findViewById(R.id.acceleration);
        iView = (TextView) findViewById(R.id.i_value);
        timeView = (TextView) findViewById(R.id.time_text);
        i = 0;
        time = 0;
        finalTime = 0;
        begin = System.currentTimeMillis();
	}
	
	public void goBack(View view)
	{
		Intent intent = new Intent(this, MiniGameMain.class);
		startActivity(intent);
	}
	
	public void goToNext(View view)
	{
		if(i>=100)
		{
			Intent intent = new Intent(this, ScoreActivity.class);
			Global.incrementTries();
			Global.setScores(sec);
			/*String message = timeView.getText().toString();
		    intent.putExtra(EXTRA_MESSAGE, message);*/
			startActivity(intent);
		}
		else
		{
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.orientation, menu);
		return true;
	}


	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		
	}


	@Override
	public void onSensorChanged(SensorEvent event) {
		time = System.currentTimeMillis()-begin;
		if(i<100)
		{
			finalTime = time;
		}
		sec = finalTime/1000.0f;
		timeView.setText("Tid: " + sec + " sekunder");
		if (i<100)
		{
			if(event.values[1]<-5)
			{
				acceleration.setText("FOR MEGET");
				i=i*0.8;
				if(i<9)
				{
					iView.setText("Points: 0" + Math.round(i));	
					iView.setTextColor(Color.parseColor("#FF0000"));
				}
				else
				{
					iView.setText("Points: " + Math.round(i));	
					iView.setTextColor(Color.parseColor("#FF0000"));
				}
			}
	
			else
			{
				if(event.values[1]<0)
				{
					if(i<20){
						acceleration.setText("SÅDAN!");
						if(i<9)
						{
							iView.setText("Points: 0" + Math.round(i));
							iView.setTextColor(Color.parseColor("#00FF00"));
							i=i+1*((Math.abs(event.values[1])*0.5));	
						}
						else
						{
							iView.setText("Points: " + Math.round(i));
							iView.setTextColor(Color.parseColor("#00FF00"));
							i=i+1*((Math.abs(event.values[1])*0.5));	
						}
					}
					else if(i>20 && i<50){
						acceleration.setText("DU PÅ VEJ!");
						if(i<9)
						{
							iView.setText("Points: 0" + Math.round(i));
							iView.setTextColor(Color.parseColor("#00FF00"));
							i=i+1*((Math.abs(event.values[1])*0.5));	
						}
						else
						{
							iView.setText("Points: " + Math.round(i));
							iView.setTextColor(Color.parseColor("#00FF00"));
							i=i+1*((Math.abs(event.values[1])*0.5));	
						}		
					}
					else if(i>50 && i< 80){
						acceleration.setText("HALVVEJS!");
						if(i<9)
						{
							iView.setText("Points: 0" + Math.round(i));
							iView.setTextColor(Color.parseColor("#00FF00"));
							i=i+1*((Math.abs(event.values[1])*0.5));	
						}
						else
						{
							iView.setText("Points: " + Math.round(i));
							iView.setTextColor(Color.parseColor("#00FF00"));
							i=i+1*((Math.abs(event.values[1])*0.5));	
						}			
					}
					else if(i>80 && i< 100){
						acceleration.setText("DU ER DER NÆSTEN!");
						if(i<9)
						{
							iView.setText("Points: 0" + Math.round(i));
							iView.setTextColor(Color.parseColor("#00FF00"));
							i=i+1*((Math.abs(event.values[1])*0.5));	
						}
						else
						{
							iView.setText("Points: " + Math.round(i));
							iView.setTextColor(Color.parseColor("#00FF00"));
							i=i+1*((Math.abs(event.values[1])*0.5));	
						}	
					}
				}
				else
				{
					acceleration.setText("TILT DEN MERE!");
					if(i<9)
					{
						iView.setText("Points: 0" + Math.round(i));	
						iView.setTextColor(Color.parseColor("#AAAAAA"));
					}
					else
					{
						iView.setText("Points: " + Math.round(i));
						iView.setTextColor(Color.parseColor("#AAAAAA"));
					}
				}
			}
		}
		else
		{
		
		acceleration.setText("GODT KLARET!!");
		i=100;
		iView.setText("Points: " + Math.round(i));
		}
		
	}
    
}