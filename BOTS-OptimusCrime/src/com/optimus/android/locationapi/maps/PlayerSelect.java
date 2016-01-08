package com.optimus.android.locationapi.maps;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class PlayerSelect extends Activity {
	
	private SharedPreferences pref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_select);
		
		TabHost tabHost=(TabHost)findViewById(R.id.tabhost);
		tabHost.setup();

		TabSpec spec1=tabHost.newTabSpec("Tab 1");
		spec1.setContent(R.id.tab1);
		spec1.setIndicator("Anna");

		TabSpec spec2=tabHost.newTabSpec("Tab 2");
		spec2.setIndicator("Ivan");
		spec2.setContent(R.id.tab2);

		TabSpec spec3=tabHost.newTabSpec("Tab 3");
		spec3.setIndicator("Bent");
		spec3.setContent(R.id.tab3);

		tabHost.addTab(spec1);
		tabHost.addTab(spec2);
		tabHost.addTab(spec3);
	}
	
	public void goAnna(View view)
	{
		Global.setPlayer("Anna");
		pref = getSharedPreferences("gameState", 0);
		SharedPreferences.Editor ed = pref.edit();
    	ed.putString("player", "Anna");
    	ed.commit();
		Intent intent = new Intent(this, PlayerChosen.class);
		startActivity(intent);
	}
	
	
	public void goIvan(View view)
	{
		Global.setPlayer("Ivan");
		pref = getSharedPreferences("gameState", 0);
		SharedPreferences.Editor ed = pref.edit();
    	ed.putString("player", "Ivan");
    	ed.commit();
		Intent intent = new Intent(this, PlayerChosen.class);
		startActivity(intent);
	}
	
	public void goBent(View view)
	{
		Global.setPlayer("Bent");
		pref = getSharedPreferences("gameState", 0);
		SharedPreferences.Editor ed = pref.edit();
    	ed.putString("player", "Bent");
    	ed.commit();
		Intent intent = new Intent(this, PlayerChosen.class);
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_select, menu);
		return true;
	}

}
