package com.optimus.android.locationapi.maps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MiniGameMain extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mini_game_main);
		
		TextView t = (TextView) findViewById(R.id.textViewHighscore);
		if(Global.getScore() != 1000.1){
			t.setText("Highscore: " + Global.getScore() + " sekunder");
		}
	}
	
	public void startGame(View view)
	{
		Intent intent = new Intent(this, OrientationActivity.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mini_game_main, menu);
		return true;
	}

}
