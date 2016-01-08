import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class RequestResponse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RequestResponse() {
		super();

		Globals.setGameOver(false);
		Globals.setWinner("");
		Globals.setWinnerVotes(0);

		Globals.putFak("Kemi", "Endnu ikke overtaget");
		Globals.putFak("Matematik", "Endnu ikke overtaget");
		Globals.putFak("Datalogi", "Endnu ikke overtaget");
		Globals.putFak("Dansk", "Endnu ikke overtaget");
		Globals.putFak("Spansk", "Endnu ikke overtaget");
		Globals.putFak("HA", "Endnu ikke overtaget");
		Globals.putFak("Økonomi", "Endnu ikke overtaget");

		Globals.putLastEntry("Kemi", "");
		Globals.putLastEntry("Matematik", "");
		Globals.putLastEntry("Datalogi", "");
		Globals.putLastEntry("Dansk", "");
		Globals.putLastEntry("Spansk", "");
		Globals.putLastEntry("HA", "");
		Globals.putLastEntry("Økonomi", "");

		Globals.putOpenFak("Kemi", true);
		Globals.putOpenFak("Matematik", true);
		Globals.putOpenFak("Datalogi", true);
		Globals.putOpenFak("Dansk", true);
		Globals.putOpenFak("Spansk", true);
		Globals.putOpenFak("HA", true);
		Globals.putOpenFak("Økonomi", true);

		Globals.putTimeFak("Kemi", 4070908800999999999L);
		Globals.putTimeFak("Matematik", 4070908800999999999L);
		Globals.putTimeFak("Datalogi", 4070908800999999999L);
		Globals.putTimeFak("Dansk", 4070908800999999999L);
		Globals.putTimeFak("Spansk", 4070908800999999999L);
		Globals.putTimeFak("HA", 4070908800999999999L);
		Globals.putTimeFak("Økonomi", 4070908800999999999L);

		Globals.putScore("Kemi", 1000.1F);
		Globals.putScore("Matematik", 1000.1F);
		Globals.putScore("Datalogi", 1000.1F);
		Globals.putScore("Dansk", 1000.1F);
		Globals.putScore("Spansk", 1000.1F);
		Globals.putScore("HA", 1000.1F);
		Globals.putScore("Økonomi", 1000.1F);
	}

	protected void doGet(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException 
	{

	}

	protected void doPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws ServletException, IOException 
	{
		try{
			BufferedReader br = servletRequest.getReader();
			JSONObject json = new JSONObject(br.readLine());
			System.out.println(json.toString());
			
			JSONObject jsonResponse = new JSONObject();
			try {
				if(json.has("player"))
				{
					if(Globals.getAllPlayers().isEmpty())
					{
						Globals.putPlayer((String) json.get("player"), (String) json.get("name"));
						Globals.putHelper((String) json.get("player"), false);
						Globals.putHangover((String) json.get("player"), false);
						Globals.setHangoverTime((String) json.get("player"), 0);
						Globals.setHelperTime((String) json.get("player"), 0);
						Globals.setVotes((String) json.get("player"), 0);
					}
					if(Globals.getAllPlayers().get(json.get("player")) == null)
					{
						Globals.putPlayer((String) json.get("player"), (String) json.get("name"));
						Globals.putHelper((String) json.get("player"), false);
						Globals.putHangover((String) json.get("player"), false);
						Globals.setHangoverTime((String) json.get("player"), 0);
						Globals.setHelperTime((String) json.get("player"), 0);
						Globals.setVotes((String) json.get("player"), 0);
					}
					else if(Globals.getAllPlayers().get(json.get("player")) != null)
					{
						if(json.has("fak"))
						{
							if(json.has("score"))
							{
								String fak = (String) json.get("fak");
								if(Globals.getTimeFak((String) json.get("fak"))>System.currentTimeMillis() && Float.parseFloat((String) json.get("score"))<Globals.getScore(fak) && !Globals.getLastEntry((String) json.get("fak")).equals(json.get("player"))  )
								{
									Globals.putScore(fak, Float.parseFloat((String) json.get("score")));
									Globals.putTimeFak((String) json.get("fak"), System.currentTimeMillis() + 3600000*24);
									Globals.putLastEntry(fak, (String) json.get("player"));
									System.out.println(Globals.getTimeFak((String) json.get("fak")));
								}
								else if(Globals.getTimeFak((String) json.get("fak"))<System.currentTimeMillis())
								{
									Globals.putOpenFak((String) json.get("fak"), false);
									Globals.putFak((String) json.get("fak"), Globals.getPlayer(Globals.getLastEntry((String) json.get("fak"))));
									if(!Globals.getAllOpenFak().containsValue(true))
									{
										Globals.setGameOver(true);
										int high = 0;
										String winner = "";
										List<String> keyList = new ArrayList<String>(Globals.getAllVotes().keySet());
										List<Integer> valueList = new ArrayList<Integer>(Globals.getAllVotes().values());
										for(int i = 0; i < keyList.size(); i++)
										{
											if (valueList.get(i) > high) {
												high = valueList.get(i);
												winner = keyList.get(i);
											}
										}
										Globals.setWinner(Globals.getPlayer(winner));
										Globals.setWinnerVotes(Globals.getVotes(winner));
									}
								}
							}
							else if(json.has("getfak"))
							{
								if(json.get("getfak").equals("owner"))
								{
									jsonResponse.put("owner", Globals.getFak((String) json.get("fak")));
								}
								if(json.getString("getfak").equals("score"))
								{
									jsonResponse.put("score", Globals.getScore((String) json.get("fak")));
								}
								if(json.get("getfak").equals("open"))
								{
									if(!Globals.getLastEntry((String) json.get("fak")).equals((String) json.get("player")))
									{
										if(Globals.getTimeFak((String) json.get("fak"))<System.currentTimeMillis())
										{
											Globals.putOpenFak((String) json.get("fak"), false);
											Globals.putFak((String) json.get("fak"), Globals.getPlayer(Globals.getLastEntry((String) json.get("fak"))));
											Globals.setVotes(Globals.getLastEntry((String) json.get("fak")), 200+(Globals.getVotes((String) json.get("player"))));
										}
										Globals.putOpenFak((String) json.get("fak"), false);
										Globals.putFak((String) json.get("fak"), Globals.getPlayer(Globals.getLastEntry((String) json.get("fak"))));
										if(!Globals.getAllOpenFak().containsValue(true))
										{
											Globals.setGameOver(true);
											int high = 0;
											String winner = "";
											List<String> keyList = new ArrayList<String>(Globals.getAllVotes().keySet());
											List<Integer> valueList = new ArrayList<Integer>(Globals.getAllVotes().values());
											for(int i = 0; i < keyList.size(); i++)
											{
												if (valueList.get(i) > high) {
													high = valueList.get(i);
													winner = keyList.get(i);
												}
											}
											Globals.setWinner(Globals.getPlayer(winner));
											Globals.setWinnerVotes(Globals.getVotes(winner));
										}
										jsonResponse.put("open", Globals.getOpenFak((String) json.get("fak")));
									}
									else
									{
										jsonResponse.put("open", false);
									}
								}
							}
							if(json.has("setfak"))
							{
								if(json.get("setfak").equals("false"))
								{
									Globals.putOpenFak((String) json.get("fak"), false);
								}
								if(json.get("setfak").equals("true"))
								{
									Globals.putOpenFak((String) json.get("fak"), true);
								}
							}
							else if(json.has("putfak"))
							{
								Globals.putFak((String) json.get("fak"), Globals.getPlayer((String) json.get("player")));
								Globals.setVotes((String) json.get("player"), 200+(Globals.getVotes((String) json.get("player"))));
								Globals.putOpenFak((String) json.get("fak"), false);
								if(!Globals.getAllOpenFak().containsValue(true))
								{
									Globals.setGameOver(true);
									int high = 0;
									String winner = "";
									List<String> keyList = new ArrayList<String>(Globals.getAllVotes().keySet());
									List<Integer> valueList = new ArrayList<Integer>(Globals.getAllVotes().values());
									for(int i = 0; i < keyList.size(); i++)
									{
										if (valueList.get(i) > high) {
											high = valueList.get(i);
											winner = keyList.get(i);
										}
									}
									Globals.setWinner(Globals.getPlayer(winner));
									Globals.setWinnerVotes(Globals.getVotes(winner));
								}
							}
						}
						if(json.has("fakGet"))
						{
							if(json.get("fakGet").equals("all"))
							{
								List<String> keyList = new ArrayList<String>(Globals.getAllFak().keySet());
								List<String> valueList = new ArrayList<String>(Globals.getAllFak().values());
								for(int i = 0; i < keyList.size(); i++)
								{
									jsonResponse.put(keyList.get(i), valueList.get(i));
								}
	
							}
						}
						if(json.has("votes"))
						{
							if(json.get("votes") instanceof Integer)
							{
								int i = Globals.getVotes("player");
								Globals.setVotes((String) json.get("player"), i+((int) json.get("votes")));
							}
							else if(json.get("votes").equals("get"))
							{
								jsonResponse.put("votes", Globals.getVotes((String) json.get("player")));
							}
							else if(json.get("votes").equals("getall"))
							{
								List<String> keyList = new ArrayList<String>(Globals.getAllVotes().keySet());
								List<Integer> valueList = new ArrayList<Integer>(Globals.getAllVotes().values());
								for(int i = 0; i < keyList.size(); i++)
								{
									jsonResponse.put(Globals.getPlayer(keyList.get(i)), valueList.get(i));
								}
							}
						}
						if(json.has("hangover"))
						{
							if(json.get("hangover").equals("true"))
							{
								Globals.putHangover((String) json.get("player"), true);
								Globals.setHangoverTime((String) json.get("player"), System.currentTimeMillis() + 3600000*10);
								Globals.setVotes((String) json.get("player"), 50+(Globals.getVotes((String) json.get("player"))));
							}
							else if(json.get("hangover").equals("false"))
							{
								Globals.putHangover((String) json.get("player"), false);
							}
							else if(json.get("hangover").equals("get"))
							{
								if(System.currentTimeMillis()>Globals.getHangoverTime((String) json.get("player")))
								{
									Globals.putHangover((String) json.get("player"), false);
								}
								jsonResponse.put("hangover", Globals.getHangover((String) json.get("player")));
							}
						}
						if(json.has("helper"))
						{
							if(json.get("helper").equals("true"))
							{
								Globals.setHelperTime((String) json.get("player"), System.currentTimeMillis() + 3600000*22);
							}
							else if(json.get("helper").equals("get"))
							{
								if(System.currentTimeMillis()>Globals.getHelperTime((String) json.get("player")))
								{
									Globals.putHelper((String) json.get("player"), false);
								}
								else if(System.currentTimeMillis()<Globals.getHelperTime((String) json.get("player")) && System.currentTimeMillis()<Globals.getHangoverTime((String) json.get("player")))
								{
									Globals.putHelper((String) json.get("player"), false);
								}
								else if(System.currentTimeMillis()<Globals.getHelperTime((String) json.get("player")) && System.currentTimeMillis()>Globals.getHangoverTime((String) json.get("player")))
								{
									Globals.putHelper((String) json.get("player"), true);
								}
								jsonResponse.put("helper", Globals.getHelper((String) json.get("player")));
							}
						}
						if(json.has("gameover") && json.has("winner"))
						{
							if(Globals.getGameOver() == true)
							{
								jsonResponse.put("gameover", Globals.getGameOver());
								jsonResponse.put("winner", Globals.getWinner());
								jsonResponse.put("votes", Globals.getWinnerVotes());
	
	
	
							}
							else
							{
								jsonResponse.put("gameover", Globals.getGameOver());
							}
						}
					}
				}
				
			} catch (JSONException e) {
				e.printStackTrace();
			}

			servletResponse.setContentType("text");
			PrintWriter localPrintWriter = servletResponse.getWriter();
			localPrintWriter.println(jsonResponse.toString());

			System.out.println(jsonResponse.toString());
		} 
		catch (IOException e) {


			try{
				servletResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				servletResponse.getWriter().print(e.getMessage());
				servletResponse.getWriter().close();
			} 
			catch (IOException ioe) 
			{

			}
		} catch (JSONException e) {
			e.printStackTrace();
		}   
	}
}



