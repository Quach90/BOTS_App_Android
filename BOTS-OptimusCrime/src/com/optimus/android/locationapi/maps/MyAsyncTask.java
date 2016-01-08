package com.optimus.android.locationapi.maps;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class MyAsyncTask extends AsyncTask<String, String, String>{
	HttpClient httpClient = new DefaultHttpClient();
	HttpPost httpPost = new HttpPost("http://84.238.86.69:8080/AndroidServletTest/testetest");
	String s;
	
	
	@Override
	protected String doInBackground(String... params) {
		// Building post parameters
        // key and value pair
		JSONObject jsonParam = new JSONObject();
		try {
			jsonParam.put("player", Global.getDeviceId());
			jsonParam.put("name", Global.getPhoneName());

			int i = params.length;
			
			if(i==2)
			{
				jsonParam.put(params[0], params[1]);
			}
			
			else if(i==4)
			{
				jsonParam.put(params[0], params[1]);
				jsonParam.put(params[2], params[3]);
			}	
			
			//Global.setValue2(jsonParam.toString());
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
        // Url Encoding the POST parameters
        try {
            httpPost.setEntity(new StringEntity(jsonParam.toString()));
        } catch (UnsupportedEncodingException e) {
            // writing error to Logh
            e.printStackTrace();
        }
 
        // Making HTTP Request
        try {
            HttpResponse response = httpClient.execute(httpPost);
            
            String responseBody = EntityUtils.toString(response.getEntity());
            s = responseBody;
            // writing response to log
            Log.d("Http Response:", response.toString()+"\n" + responseBody);

        } catch (ClientProtocolException e) {
            // writing exception to log
            e.printStackTrace();
        } catch (IOException e) {
            // writing exception to log
            e.printStackTrace();
        }
        
        
		return s;
	}
	
	protected void onPostExecute(String result){
		super.onPostExecute(result);
	}

}
