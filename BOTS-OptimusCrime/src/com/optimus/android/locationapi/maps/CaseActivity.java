package com.optimus.android.locationapi.maps;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CaseActivity extends Activity {

	String player;
	int caseNo;
	TextView t;
	Button b;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_kato);

		
        player = Global.getPlayer();
        caseNo = Global.getCaseNo();
        setCase(player,caseNo);
	}
	
	public void setCase(String p, int i){
		b = (Button) findViewById(R.id.case_button1);
		b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                next();
            }
        });
		t = (TextView) findViewById(R.id.case_text);
		t.setBackgroundColor(Color.parseColor("#000000"));
		t.setTextColor(Color.parseColor("#FFFFFF"));
		if(p.equals("Anna")){
			if(i == 1)
				t.setText(getResources().getString(R.string.anna_case1));
			if(i == 2)
				t.setText(getResources().getString(R.string.anna_case2));
			if(i == 3)
				t.setText(getResources().getString(R.string.anna_case3));
			if(i == 4)
				t.setText(getResources().getString(R.string.anna_case4));
			if(i == 5)
				t.setText(getResources().getString(R.string.anna_case5));
			if(i == 6)
				t.setText(getResources().getString(R.string.anna_case6));
			if(i == 7)
				t.setText(getResources().getString(R.string.anna_case7));
			if(i == 8)
				t.setText(getResources().getString(R.string.anna_case8));
			if(i == 9)
				t.setText(getResources().getString(R.string.anna_case9));
		}
		if(p.equals("Ivan")){
			if(i == 1)
				t.setText(getResources().getString(R.string.ivan_case1));
			if(i == 2)
				t.setText(getResources().getString(R.string.ivan_case2));
			if(i == 3)
				t.setText(getResources().getString(R.string.ivan_case3));
			if(i == 4)
				t.setText(getResources().getString(R.string.ivan_case4));
			if(i == 5)
				t.setText(getResources().getString(R.string.ivan_case5));
			if(i == 6)
				t.setText(getResources().getString(R.string.ivan_case6));
			if(i == 7)
				t.setText(getResources().getString(R.string.ivan_case7));
			if(i == 8)
				t.setText(getResources().getString(R.string.ivan_case8));
			if(i == 9)
				t.setText(getResources().getString(R.string.ivan_case9));
		}
		if(p.equals("Bent")){
			if(i == 1)
				t.setText(getResources().getString(R.string.bent_case1));
			if(i == 2)
				t.setText(getResources().getString(R.string.bent_case2));
			if(i == 3)
				t.setText(getResources().getString(R.string.bent_case3));
			if(i == 4)
				t.setText(getResources().getString(R.string.bent_case4));
			if(i == 5)
				t.setText(getResources().getString(R.string.bent_case5));
			if(i == 6)
				t.setText(getResources().getString(R.string.bent_case6));
			if(i == 7)
				t.setText(getResources().getString(R.string.bent_case7));
			if(i == 8)
				t.setText(getResources().getString(R.string.bent_case8));
			if(i == 9)
				t.setText(getResources().getString(R.string.bent_case9));
		}
	}
	
	public void next(){
		b = (Button) findViewById(R.id.case_button1);
		t.setBackgroundColor(Color.parseColor("#FFFFFF"));
		t.setTextColor(Color.parseColor("#000000"));
		if(player.equals("Anna")){
			if(caseNo == 1)
				t.setText(getResources().getString(R.string.anna_case1_sms));
			if(caseNo == 2)
				t.setText(getResources().getString(R.string.anna_case2_sms));
			if(caseNo == 3)
				t.setText(getResources().getString(R.string.anna_case3_sms));
			if(caseNo == 4)
				t.setText(getResources().getString(R.string.anna_case4_sms));
			if(caseNo == 5)
				t.setText(getResources().getString(R.string.anna_case5_sms));
			if(caseNo == 6)
				t.setText(getResources().getString(R.string.anna_case6_sms));
			if(caseNo == 7)
				t.setText(getResources().getString(R.string.anna_case7_sms));
			if(caseNo == 8)
				t.setText(getResources().getString(R.string.anna_case8_sms));
			if(caseNo == 9)
				t.setText(getResources().getString(R.string.anna_case9_sms));
		}
		if(player.equals("Ivan")){
			if(caseNo == 1)
				t.setText(getResources().getString(R.string.ivan_case1_sms));
			if(caseNo == 2)
				t.setText(getResources().getString(R.string.ivan_case2_sms));
			if(caseNo == 3)
				t.setText(getResources().getString(R.string.ivan_case3_sms));
			if(caseNo == 4)
				t.setText(getResources().getString(R.string.ivan_case4_sms));
			if(caseNo == 5)
				t.setText(getResources().getString(R.string.ivan_case5_sms));
			if(caseNo == 6)
				t.setText(getResources().getString(R.string.ivan_case6_sms));
			if(caseNo == 7)
				t.setText(getResources().getString(R.string.ivan_case7_sms));
			if(caseNo == 8)
				t.setText(getResources().getString(R.string.ivan_case8_sms));
			if(caseNo == 9)
				t.setText(getResources().getString(R.string.ivan_case9_sms));
		}
		if(player.equals("Bent")){
			if(caseNo == 1)
				t.setText(getResources().getString(R.string.bent_case1_sms));
			if(caseNo == 2)
				t.setText(getResources().getString(R.string.bent_case2_sms));
			if(caseNo == 3)
				t.setText(getResources().getString(R.string.bent_case3_sms));
			if(caseNo == 4)
				t.setText(getResources().getString(R.string.bent_case4_sms));
			if(caseNo == 5)
				t.setText(getResources().getString(R.string.bent_case5_sms));
			if(caseNo == 6)
				t.setText(getResources().getString(R.string.bent_case6_sms));
			if(caseNo == 7)
				t.setText(getResources().getString(R.string.bent_case7_sms));
			if(caseNo == 8)
				t.setText(getResources().getString(R.string.bent_case8_sms));
			if(caseNo == 9)
				t.setText(getResources().getString(R.string.bent_case9_sms));
		}
		//b.setText("Gå til kort");
		b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	dialogBox("Lady Gaga");
            }
        });
	}
	
	public void kort(){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void byTur(){
		Global.nowHangover();
		Intent intent = new Intent(this, CityActivity.class);
		startActivity(intent);
	}
	
	public void dialogBox(String s){
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialog, int which) { 
				switch (which) { 
				case DialogInterface.BUTTON_POSITIVE: // Yes button clicked 
					byTur();
					
				break; 
				
				case DialogInterface.BUTTON_NEGATIVE: 
					kort();
					break; 
					} 
				} 
			}; 
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			builder.setMessage("Vil du i byen med " + s + "?") 
			.setPositiveButton("Ja", dialogClickListener) 
			.setNegativeButton("Nej", dialogClickListener).show(); 
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.kato, menu);
        return true;
    }
}
