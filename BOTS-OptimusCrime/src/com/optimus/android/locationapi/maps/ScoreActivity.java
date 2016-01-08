package com.optimus.android.locationapi.maps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

import android.widget.TextView;

public class ScoreActivity extends Activity {
	TextView tView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score);
		
	    // Get the message from the intent
	    //Intent intent = getIntent();
	    //String message = intent.getStringExtra(OrientationActivity.EXTRA_MESSAGE);
	    
	    // Set the text view as the activity layout
	    LinearLayout ll = (LinearLayout) findViewById(R.id.scroll_layout);
	    for(int i = 0; i<Global.getScores().size(); i++)
	    {
		    TextView textView = new TextView(this);
		    textView.setTextSize(20);
		    textView.setText(i+1 + ". " + Global.getScores().get(i).toString());
		    ll.addView(textView);
	    }
	    tView = (TextView) findViewById(R.id.textView1);
	    if(Global.getTries()>2)
	    {
	    	tView.setText(R.string.tap_text);
	    }
	    
	    
	}
	
	public void goBack(View view)
	{
		if(Global.getTries()<3)
		{
			Intent intent = new Intent(this, MiniGameMain.class);
			startActivity(intent);	
		}
		else
		{
			Global.uploadScore();
			Global.resetTries();
			String currentF = Global.getInFaculty();
			int currentNo = Global.getCaseNo();
			boolean skift = false;
			if(currentNo == 0){
				if(currentF.equals("Kemi") || currentF.equals("Matematik") || currentF.equals("Datalogi")){
					Global.setCaseNo(1);
					skift = true;
				}
				if(currentF.equals("Dansk") || currentF.equals("Spansk")){
					Global.setCaseNo(2);
					skift = true;
				}
				if(currentF.equals("HA") || currentF.equals("Økonomi")){
					Global.setCaseNo(3);
					skift = true;
				}
			}
			if(currentNo == 1){
				if(currentF.equals("Dansk") || currentF.equals("Spansk")){
					Global.setCaseNo(4);
					skift = true;
				}
				if(currentF.equals("HA") || currentF.equals("Økonomi")){
					Global.setCaseNo(5);
					skift = true;
				}
			}
			if(currentNo == 2){
				if(currentF.equals("Kemi") || currentF.equals("Matematik") || currentF.equals("Datalogi")){
					Global.setCaseNo(4);
					skift = true;
				}
				if(currentF.equals("HA") || currentF.equals("Økonomi")){
					Global.setCaseNo(6);
					skift = true;
				}
			}
			if(currentNo == 3){
				if(currentF.equals("Kemi") || currentF.equals("Matematik") || currentF.equals("Datalogi")){
					Global.setCaseNo(5);
					skift = true;
				}
				if(currentF.equals("Dansk") || currentF.equals("Spansk")){
					Global.setCaseNo(6);
					skift = true;
				}
			}
			if(currentNo == 4){
				if(currentF.equals("HA") || currentF.equals("Økonomi")){
					Global.setCaseNo(9);
					skift = true;
				}
			}
			if(currentNo == 5){
				if(currentF.equals("Dansk") || currentF.equals("Spansk")){
					Global.setCaseNo(7);
					skift = true;
				}
			}
			if(currentNo == 6){
				if(currentF.equals("Kemi") || currentF.equals("Matematik") || currentF.equals("Datalogi")){
					Global.setCaseNo(8);
					skift = true;
				}
			}
			
			if(skift){
				Intent intent = new Intent(this, CaseActivity.class);
				startActivity(intent);
			}
			else{
				Intent intent = new Intent(this, MainActivity.class);
				startActivity(intent);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score, menu);
		return true;
	}

}
