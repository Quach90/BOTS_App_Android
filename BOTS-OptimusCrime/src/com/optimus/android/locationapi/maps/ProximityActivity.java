package com.optimus.android.locationapi.maps;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.optimus.android.locationapi.maps.R;



public class ProximityActivity extends Activity {
	
	String notificationTitle;
	String notificationContent;
	String tickerMessage;
	String notificationFaculty;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		boolean proximity_entering = getIntent().getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true);
		
		double lat = getIntent().getDoubleExtra("lat", 0);
		
		double lng = getIntent().getDoubleExtra("lng", 0);
		
		String faculty = getIntent().getStringExtra("faculty");
		
		String strLocation = Double.toString(lat)+","+Double.toString(lng);
		
		if(proximity_entering){
			Toast.makeText(getBaseContext(),"Du er indenfor" + faculty  ,Toast.LENGTH_LONG).show();
			notificationTitle = "Proximity - Entered";
			notificationContent = "Du er indenfor rækkevidden til locationen:" + strLocation;
			notificationFaculty = "Dette område tilhører: " + faculty;
			tickerMessage = "Ankommet til:" + faculty; 
			Global.setInFaculty(faculty);
			if(Global.isOpenFaculty() && !Global.isHangover()){
				Global.setMapDialog(true);
				//Intent intent = new Intent(this, MainActivity.class);
				//startActivity(intent);
			}
			else if(Global.isHangover()){
				Toast.makeText(getBaseContext(),"Du har tømmermænd og kan ikke spille"  ,Toast.LENGTH_LONG).show();
			}
			else if(!Global.isOpenFaculty()){
				Toast.makeText(getBaseContext(),"Fakultetet er allerede overtaget"  ,Toast.LENGTH_LONG).show();
			}
		}else{
			Toast.makeText(getBaseContext(),"Du har forladt" + faculty  ,Toast.LENGTH_LONG).show();
			notificationTitle = "Proximity - Exit";
			notificationContent = "Forladt:" + strLocation;
			tickerMessage = "Forladt:" + faculty;
		}
		
		
		
		Intent notificationIntent = new Intent(getApplicationContext(),MainActivity.class);
		
		/** Adding content to the notificationIntent, which will be displayed on 
		 * viewing the notification
		 */
		notificationIntent.putExtra("content", notificationContent );
		
		notificationIntent.putExtra("faculty", notificationFaculty );
		
		/** This is needed to make this intent different from its previous intents */
		notificationIntent.setData(Uri.parse("tel:/"+ (int)System.currentTimeMillis()));
		
		/** Creating different tasks for each notification. See the flag Intent.FLAG_ACTIVITY_NEW_TASK */
		PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, Intent.FLAG_ACTIVITY_NEW_TASK);

		/** Getting the System service NotificationManager */
        NotificationManager nManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
		
        /** Configuring notification builder to create a notification */
		NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(getApplicationContext())
							.setWhen(System.currentTimeMillis())
							.setContentText(notificationContent)
							.setContentTitle(notificationTitle)
							.setSmallIcon(R.drawable.test)
							.setAutoCancel(true)
							.setTicker(tickerMessage)
							.setContentIntent(pendingIntent)
							.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
		
		
		/** Creating a notification from the notification builder */
		Notification notification = notificationBuilder.build();
		
		/** Sending the notification to system. 
		 * The first argument ensures that each notification is having a unique id 
		 * If two notifications share same notification id, then the last notification replaces the first notification 
		 * */
		nManager.notify((int)System.currentTimeMillis(), notification);
		
		/** Finishes the execution of this activity */
		finish();
		
		
	}
}
