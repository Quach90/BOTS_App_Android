package com.optimus.android.locationapi.maps;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;
import android.graphics.Color;

public class ProgressActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_progress);
		
		
		
		TableLayout tl = (TableLayout) findViewById(R.id.table_layout);		
		
		JSONObject fakulteter = Global.getAllFak();
		@SuppressWarnings("unchecked")
		Iterator<String> itr = fakulteter.keys();
		int i = 0;
			while(itr.hasNext())
			{
				if(i==0)
				{
					String s = (String) itr.next();
					System.out.print(s);
					TableRow tr = new TableRow(this);
					TextView tv = new TextView(this);
					LayoutParams layout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					layout.weight = 1;
					layout.setMargins(2, 2, 2, 2);
					tv.setWidth(0);
					tv.setBackgroundColor(Color.parseColor ("#dddddd"));
					tv.setLayoutParams(layout);
					TextView tv2 = new TextView(this);
					tv2.setWidth(0);
					tv2.setBackgroundColor(Color.parseColor ("#dddddd"));
					tv2.setLayoutParams(layout);
					tv.setText(s);
					try {
						tv2.setText((CharSequence) fakulteter.get(s));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tr.addView(tv);
					tr.addView(tv2);
					tl.addView(tr, 2);
					i++;
				}
				else
				{	
					String s = (String) itr.next();
					System.out.print(s);
					TableRow tr = new TableRow(this);
					TextView tv = new TextView(this);
					LayoutParams layout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
					layout.weight = 1;
					layout.setMargins(2, 2, 2, 2);
					tv.setWidth(0);
					tv.setBackgroundColor(Color.parseColor ("#999999"));
					tv.setTextColor(Color.parseColor ("#ffffff"));
					tv.setLayoutParams(layout);
					TextView tv2 = new TextView(this);
					tv2.setWidth(0);
					tv2.setBackgroundColor(Color.parseColor ("#999999"));
					tv2.setTextColor(Color.parseColor ("#ffffff"));
					tv2.setLayoutParams(layout);
					tv.setText(s);
					try {
						tv2.setText((CharSequence) fakulteter.get(s));
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tr.addView(tv);
					tr.addView(tv2);
					tl.addView(tr, 2);
					i--;
				}
			}

			JSONObject stemmer = Global.getAllVotes();
			@SuppressWarnings("unchecked")
			Iterator<String> itr2 = stemmer.keys();
			int a = 0;
				while(itr2.hasNext())
				{
					if(a==0)
					{
						String s = (String) itr2.next();
						System.out.print(s);
						TableRow tr = new TableRow(this);
						TextView tv = new TextView(this);
						LayoutParams layout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						layout.weight = 1;
						layout.setMargins(2, 2, 2, 2);
						tv.setWidth(0);
						tv.setBackgroundColor(Color.parseColor ("#dddddd"));
						tv.setLayoutParams(layout);
						TextView tv2 = new TextView(this);
						tv2.setWidth(0);
						tv2.setBackgroundColor(Color.parseColor ("#dddddd"));
						tv2.setLayoutParams(layout);
						tv.setText(s);
						try {
							tv2.setText((CharSequence) stemmer.get(s).toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						tr.addView(tv);
						tr.addView(tv2);
						tl.addView(tr, 10);
						a++;
					}
					else
					{	
						String s = (String) itr2.next();
						System.out.print(s);
						TableRow tr = new TableRow(this);
						TextView tv = new TextView(this);
						LayoutParams layout = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
						layout.weight = 1;
						layout.setMargins(2, 2, 2, 2);
						tv.setWidth(0);
						tv.setBackgroundColor(Color.parseColor ("#999999"));
						tv.setTextColor(Color.parseColor ("#ffffff"));
						tv.setLayoutParams(layout);
						TextView tv2 = new TextView(this);
						tv2.setWidth(0);
						tv2.setBackgroundColor(Color.parseColor ("#999999"));
						tv2.setTextColor(Color.parseColor ("#ffffff"));
						tv2.setLayoutParams(layout);
						tv.setText(s);
						try {
							tv2.setText((CharSequence) stemmer.get(s).toString());
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						tr.addView(tv);
						tr.addView(tv2);
						tl.addView(tr, 10);
						a--;
					}
				}
				
				checkGame();
	}
	
	public void checkGame(){
		if(Global.gameOver()){
			Intent intent = new Intent(this, EndGameActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void goBack(View view)
	{
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
