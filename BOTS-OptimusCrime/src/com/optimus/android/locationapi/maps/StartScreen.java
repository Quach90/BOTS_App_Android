package com.optimus.android.locationapi.maps;

import android.os.Bundle;
import android.provider.Settings.Secure;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class StartScreen extends Activity {
	
	private SharedPreferences pref;
	private boolean gameActive;
	private boolean deviceName;

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        Global.setDeviceId(Secure.getString(this.getContentResolver(), Secure.ANDROID_ID));
		//Global.setPhoneName(android.os.Build.DEVICE);
		
        EditText eT = (EditText) findViewById(R.id.editText1);
        
        pref = getSharedPreferences("gameState", 0);
        String player = pref.getString("player", "noPlayer");
        String device = pref.getString("device", "noName");
        Global.setCaseNo(pref.getInt("case", 0));
        
        Button b = (Button) findViewById(R.id.continueButton);
        if(player.equals("noPlayer")){
        	gameActive = false;
        }
        else{
        	b.setText("Fortsæt spil som " + player);
        	Global.setPlayer(player);
        	gameActive = true;
        }
        if(device.equals("noName")){
        	deviceName = false;
        }
        else{
        	eT.setText(device);
        	Global.setPhoneName(device);
        	deviceName = true;
        }

    }

    public void choosePlayer(View view)
    {
    	EditText eT = (EditText) findViewById(R.id.editText1);
    	if(!eT.getText().toString().equals("")){
    		String device2 = eT.getText().toString();
    		Global.setPhoneName(device2);
    		SharedPreferences.Editor ed = pref.edit();
        	ed.putString("device", device2);
        	ed.commit();
    		deviceName = true;
    	}
    	if(gameActive){
    		Toast.makeText(getBaseContext(), "Afslut først aktivt spil", Toast.LENGTH_LONG).show();
    	}
    	else if(!deviceName){
    		Toast.makeText(getBaseContext(), "Vælg venligst et spillernavn", Toast.LENGTH_LONG).show();
    	}
    	else{
    		Intent intent  = new Intent(this, PlayerSelect.class);
    		startActivity(intent);
    	}
    }
    
    public void resumeGame(View view)
    {
    	if(gameActive){
    	Intent intent  = new Intent(this, MainActivity.class);
    	startActivity(intent);
    	}
    	else{
    		Toast.makeText(getBaseContext(), "Intet aktivt spil", Toast.LENGTH_LONG).show();
    	}
    }
    
    public void resetGame(View view){
    	SharedPreferences.Editor ed = pref.edit();
    	ed.clear();
    	ed.commit();
    	pref = getSharedPreferences("location", 0);
    	ed.clear();
    	ed.commit();
    	Global.setCaseNo(0);
    	gameActive=false;
    	deviceName=false;
    	Button b = (Button) findViewById(R.id.continueButton);
    	b.setText("Fortsæt spil");
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    protected void onPause(){
    	super.onPause();
    	SharedPreferences.Editor ed = pref.edit();
    	ed.putInt("case", Global.getCaseNo());
    	ed.commit();
    }

    
}