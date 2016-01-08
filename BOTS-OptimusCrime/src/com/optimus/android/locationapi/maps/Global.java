package com.optimus.android.locationapi.maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

import android.annotation.SuppressLint;
import android.app.Application;


import org.json.JSONException;
import org.json.JSONObject;


public class Global extends Application{
	
	private static String player = "";
	private static ArrayList<Float> scores = new ArrayList<Float>();
	private static int tries = 0;
	private static String inFaculty;
	private static int caseNo = 0;
	
	public static String deviceId;
	public static String phoneName;
	public static String responseJSON;

	private static boolean openFaculty;
	private static boolean hangover;
	private static float score;
	
	private static boolean mapDialog = false;
	private static boolean gameOver;
	private static String winner;
	private static int winnerVote;
	
	public static String getWinner(){
		return winner;
	}
	
	public static int getWinnerVote(){
		return winnerVote;
	}
	
	public static boolean gameOver(){
		JSONObject json = new JSONObject();
		try {
			json = new JSONObject(new MyAsyncTask().execute("gameover", "a", "winner", "r").get());
			gameOver = (Boolean) json.get("gameover");
			winner = (String) json.get("winner");
			winnerVote = (Integer) json.get("votes"); 
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gameOver;
	}
	
	public static JSONObject getAllFak()
	{
		JSONObject json = new JSONObject();
		try {
			json = new JSONObject(new MyAsyncTask().execute("fakGet", "all").get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public static JSONObject getAllVotes()
	{
		
		JSONObject json = new JSONObject();
		try {
			json = new JSONObject(new MyAsyncTask().execute("votes", "getall").get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	
	public static boolean isMapDialog() {
		return mapDialog;
	}

	public static void setMapDialog(boolean mapDialog) {
		Global.mapDialog = mapDialog;
	}

	public Global(){
		super();
	}
	
	public static float getScore() {
		return score;
	}

	public static void setScore(float s) {
		score = s;
	}

	public static boolean isHangover() {
		return hangover;
	}

	public static void setHangover(boolean h) {
		hangover = h;
	}
	
	public static void nowHangover(){
		MyAsyncTask a = new MyAsyncTask();
		try {
			a.execute("hangover", "true", "helper", "true").get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static boolean isOpenFaculty() {
		return openFaculty;
	}

	public static void setOpenFaculty(boolean o) {
		openFaculty = o;
	}


	public static void setDeviceId(String v)
	{
		deviceId = v;
	}
	
	public static String getDeviceId()
	{
		return deviceId;
	}
	
	public static void setPhoneName(String v)
	{
		phoneName = v;
	}
	
	public static String getPhoneName()
	{
		return phoneName;
	}
	
	public static int getCaseNo() {
		return caseNo;
	}

	public static void setCaseNo(int c) {
		caseNo = c;
	}

	@SuppressLint("UseValueOf")
	public static void setInFaculty(String f){
		inFaculty = f;
		MyAsyncTask a = new MyAsyncTask();
		try {
			JSONObject r = new JSONObject(a.execute("fak", f, "getfak", "open").get());
			setOpenFaculty((Boolean) r.get("open"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyAsyncTask b = new MyAsyncTask();
		try {
			JSONObject r = new JSONObject(b.execute("hangover", "get").get());
			setHangover((Boolean) r.get("hangover"));		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MyAsyncTask c = new MyAsyncTask();
		try {
			JSONObject r = new JSONObject(c.execute("fak", f, "getfak", "score").get());
			setScore(new Float(((Double) r.get("score"))));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String getInFaculty(){
		return inFaculty;
	}
	
	public static void uploadScore(){
		MyAsyncTask a = new MyAsyncTask();
		try {
			a.execute("fak", getInFaculty(), "score", Float.toString(getScores().get(0))).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Float> getScores()
	{
		Collections.sort(scores);
		if(scores.size()==3){
			
		}
		return scores;
	}
	
	public static void setScores(Float s)
	{
		Collections.sort(scores);
		if(scores.size()==3)
		{
			if(s<scores.get(2))
			{
				scores.remove(2);
				scores.add(s);
			}
		}
		else
		{
			scores.add(s);
		}
	}
	
	public static int getTries()
	{
		return tries;
	}
	
	public static void incrementTries()
	{
		tries++;
	}
	
	public static void resetTries()
	{
		tries = 0;
	}
	
	
	public static void setPlayer(String s)
	{
		player = s;
		//MyAsyncTask a = new MyAsyncTask();
		//a.execute();
	}
	
	public static String getPlayer()
	{
		return player;
	}

}

