package com.optimus.android.locationapi.maps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class PlayerChosen extends Activity {
	TextView t;
	String s;
	String player;
	int count;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_chosen);
		
		MyAsyncTask a = new MyAsyncTask();
		a.execute();
		
        player=Global.getPlayer();
        count = 0;
        t = (TextView) findViewById(R.id.text);
		if(player.equals("Anna"))
		{
			t.setBackgroundColor(Color.parseColor("#000000"));
			t.setTextColor(Color.parseColor("#FFFFFF"));
			t.setText(getResources().getString(R.string.anna_intro2_text));
		}
		else if(player.equals("Ivan"))
		{
			t.setBackgroundColor(Color.parseColor("#000000"));
			t.setTextColor(Color.parseColor("#FFFFFF"));
			t.setText(getResources().getString(R.string.ivan_intro2_text));
		}
		else if(player.equals("Bent"))
		{
			t.setBackgroundColor(Color.parseColor("#000000"));
			t.setTextColor(Color.parseColor("#FFFFFF"));
			t.setText(getResources().getString(R.string.bent_intro2_text));
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_chosen, menu);
		return true;
	}
	
	public void viewMap(View view){
		if(count == 0){
			if(player.equals("Anna"))
			{
				t.setBackgroundColor(Color.parseColor("#FFFFFF"));
				t.setTextColor(Color.parseColor("#000000"));
				t.setText(getResources().getString(R.string.anna_intro3_text));
			}
			if(player.equals("Ivan")){
				t.setBackgroundColor(Color.parseColor("#FFFFFF"));
				t.setTextColor(Color.parseColor("#000000"));
				t.setText(getResources().getString(R.string.ivan_intro3_text));
			}
			if(player.equals("Bent")){
				t.setBackgroundColor(Color.parseColor("#FFFFFF"));
				t.setTextColor(Color.parseColor("#000000"));
				t.setText(getResources().getString(R.string.bent_intro3_text));
			}
		}
		if(count == 1){
			if(player.equals("Anna"))
			{
				t.setBackgroundColor(Color.parseColor("#000000"));
				t.setTextColor(Color.parseColor("#FFFFFF"));
				t.setText(getResources().getString(R.string.anna_intro4_text));
			}
			if(player.equals("Ivan")){
				t.setBackgroundColor(Color.parseColor("#000000"));
				t.setTextColor(Color.parseColor("#FFFFFF"));
				t.setText(getResources().getString(R.string.ivan_intro4_text));
			}
			if(player.equals("Bent")){
				t.setBackgroundColor(Color.parseColor("#000000"));
				t.setTextColor(Color.parseColor("#FFFFFF"));
				t.setText(getResources().getString(R.string.bent_intro4_text));
			}
		}
		if(count == 2){
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
			count = 1;
		}
		count++;
	}

}