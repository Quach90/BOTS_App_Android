package com.optimus.android.locationapi.maps;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class EndGameActivity extends Activity {
	
	TextView t;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_game);
		
		t = (TextView) findViewById(R.id.endTextView);
		if(Global.getWinner().equals(Global.phoneName)){
			if(Global.getPlayer().equals("Anna")){
				t.setText(getResources().getString(R.string.anna_win));
			}
			if(Global.getPlayer().equals("Ivan")){
				t.setText(getResources().getString(R.string.ivan_win));
			}
			if(Global.getPlayer().equals("Bent")){
				t.setText(getResources().getString(R.string.bent_win));
			}
		}
		else{
			if(Global.getPlayer().equals("Anna")){
				t.setText(getResources().getString(R.string.anna_lose));
			}
			if(Global.getPlayer().equals("Ivan")){
				t.setText(getResources().getString(R.string.ivan_lose));
			}
			if(Global.getPlayer().equals("Bent")){
				t.setText(getResources().getString(R.string.bent_lose));
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.end_game, menu);
		return true;
	}

}
