package com.optimus.android.locationapi.maps;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.optimus.android.locationapi.maps.R;

public class MainActivity extends FragmentActivity {
	
	GoogleMap googleMap;
    LocationManager locationManager;
    PendingIntent pendingIntent;
    LatLng myLocation;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Getting Google Play availability status
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());

        // Showing status
        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();

        }else { // Google Play Services are available        	

            // Getting reference to the SupportMapFragment of activity_main.xml
            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

            // Getting GoogleMap object from the fragment
            googleMap = fm.getMap();

            // Enabling MyLocation Layer of Google Map
            googleMap.setMyLocationEnabled(true);         

            // Getting LocationManager object from System Service LOCATION_SERVICE
            locationManager = (LocationManager) getSystemService(LOCATION_SERVICE); 
            
         	// Matematik
         	addFaculty(new LatLng(56.166617, 10.199563),"Matematik");

         	// BSS
         	addFaculty(new LatLng(56.162135, 10.182341),"HA");
         			
         	//Datalogi
         	addFaculty(new LatLng(56.172297,10.187872),"Datalogi");
         	//Hos mig
         	//addFaculty(new LatLng(56.160955,10.088351), "Datalogi");
         	
         	//Økonomi
         	addFaculty(new LatLng(56.171044, 10.204646),"Økonomi");
         	
         	//Dansk
            addFaculty(new LatLng(56.17266, 10.205708),"Dansk");
            //Til test
         	//addFaculty(new LatLng(56.171734,10.189926), "Dansk");         	
         	
            //Kemi
            addFaculty(new LatLng(56.168398,10.198884),"Kemi");
            
            //Spansk
            addFaculty(new LatLng(56.172113,10.202049),"Spansk");
            
            if(Global.isMapDialog()){
         		dialogBox(Global.getInFaculty());
         		Global.setMapDialog(false);
         	}

            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                myLocation = new LatLng(location.getLatitude(),
                        location.getLongitude());
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myLocation, 15));
            }
        
		}		
	}
	
	
	private void drawCircle(LatLng point){
		
		// Instantiating CircleOptions to draw a circle around the marker
		CircleOptions circleOptions = new CircleOptions();
		
		// Specifying the center of the circle
		circleOptions.center(point);
		
		// Radius of the circle
		circleOptions.radius(50);
		
		// Border color of the circle
		circleOptions.strokeColor(Color.BLACK);
		
		// Fill color of the circle
		circleOptions.fillColor(0x30ff0000);
		
		// Border width of the circle
		circleOptions.strokeWidth(2);
		
		// Adding the circle to the GoogleMap
		googleMap.addCircle(circleOptions);
		
	}	
	
	private void drawMarker(LatLng point, String faculty){
		// Creating an instance of MarkerOptions
		MarkerOptions markerOptions = new MarkerOptions();					
		
		// Setting latitude and longitude for the marker
		markerOptions.position(point);
		
		// Adding InfoWindow title
		markerOptions.title("Fakultet");		
		
		// Adding InfoWindow contents		
		markerOptions.snippet(faculty);
		
		// Adding marker on the Google Map
		googleMap.addMarker(markerOptions);
		
	}
	
	public void addFaculty(LatLng point, String faculty) {				 
							
		// Drawing marker on the map
		drawMarker(point, faculty);
		
		// Drawing circle on the map
		drawCircle(point);					
		
        // This intent will call the activity ProximityActivity
        Intent proximityIntent = new Intent("com.optimus.android.locationapi.maps.proximity");
        
        // Passing latitude to the PendingActivity
        proximityIntent.putExtra("lat",point.latitude);
               
        // Passing longitude to the PendingActivity
        proximityIntent.putExtra("lng", point.longitude);
        
        // Vælg fakultet og gem med activity
		proximityIntent.putExtra("faculty", faculty);
		
        // Creating a pending intent which will be invoked by LocationManager when the specified region is
        // entered or exited
        pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, proximityIntent,Intent.FLAG_ACTIVITY_NEW_TASK);
        
        // Setting proximity alert 
        // The pending intent will be invoked when the device enters or exits the region 20 meters
        // away from the marked point
        // The -1 indicates that, the monitor will not be expired
        locationManager.addProximityAlert(point.latitude, point.longitude, 50, -1, pendingIntent);		        
                
	}
	
	
	public void dialogBox(String s){
		DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() { 
			public void onClick(DialogInterface dialog, int which) { 
				switch (which) { 
				case DialogInterface.BUTTON_POSITIVE: // Yes button clicked 
					startGame();
					
				break; 
				
				case DialogInterface.BUTTON_NEGATIVE: 
					// No button clicked 
					// do nothing 
					Toast.makeText(MainActivity.this, "No Clicked", Toast.LENGTH_LONG).show();
					break; 
					} 
				} 
			}; 
			
			AlertDialog.Builder builder = new AlertDialog.Builder(this); 
			builder.setMessage("Du er indenfor " + s + " område.\nVil du overtage dette fakultet?") 
			.setPositiveButton("Ja", dialogClickListener) 
			.setNegativeButton("Nej", dialogClickListener).show(); 
	}
	
	
	public void startGame(){
		Intent intent = new Intent(this, MiniGameMain.class);
		startActivity(intent);
	}
	
	public void scoreTavle(View view){
		Intent intent = new Intent(this, ProgressActivity.class);
		startActivity(intent);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void test(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
}
