import java.util.HashMap;


public class Globals {
	private static boolean gameOver;
	private static String winner;
	private static int winnerVotes;
	private static HashMap<String, String> playerMap = new HashMap<String, String>();
	private static HashMap<String, String> fakMap = new HashMap<String, String>();
	private static HashMap<String, String> lastEntryMap = new HashMap<String, String>();
	private static HashMap<String, Long> fakTimeMap = new HashMap<String, Long>();
	private static HashMap<String, Boolean> fakOpenMap = new HashMap<String, Boolean>();
	private static HashMap<String, Float> scoreMap = new HashMap<String, Float>();
	private static HashMap<String, Integer> voteMap = new HashMap<String, Integer>();
	private static HashMap<String, Boolean> inactiveHelperMap = new HashMap<String, Boolean>();
	private static HashMap<String, Long> inactiveTimeMap = new HashMap<String, Long>();
	private static HashMap<String, Boolean> hangoverMap = new HashMap<String, Boolean>();
	private static HashMap<String, Long> hangoverTimeMap = new HashMap<String, Long>();
	
	
	public static void setGameOver(boolean b)
	{
		gameOver = b;
	}
	
	public static boolean getGameOver()
	{
		
		return gameOver;
	}
	
	public static void setWinner(String s)
	{
		winner = s;
	}
	
	public static String getWinner()
	{
		
		return winner;
	}
	public static void setWinnerVotes(int s)
	{
		winnerVotes = s;
	}
	
	public static int getWinnerVotes()
	{
		
		return winnerVotes;
	}
	
	public static void putPlayer(String s, String o)
	{
		playerMap.put(s, o);
	}
	
	public static String getPlayer(String s)
	{
		
		return playerMap.get(s);
	}
	
	public static HashMap<String, String> getAllPlayers()
	{
		return playerMap;
	}
	
	public static void putFak(String s, String o)
	{
		fakMap.put(s, o);
	}
	
	public static String getFak(String s)
	{
		return fakMap.get(s);
	}
	
	public static HashMap<String, String> getAllFak()
	{
		return fakMap;
	}
	
	public static void putLastEntry(String s, String o)
	{
		lastEntryMap.put(s, o);
	}
	
	public static String getLastEntry(String s)
	{
		return lastEntryMap.get(s);
	}
	
	public static void putTimeFak(String s, long l)
	{
		fakTimeMap.put(s, l);
	}
	
	public static long getTimeFak(String s)
	{
		return fakTimeMap.get(s);
	}
	
	public static void putOpenFak(String s, boolean b)
	{
		fakOpenMap.put(s, b);
	}
	
	public static boolean getOpenFak(String s)
	{
		return fakOpenMap.get(s);
	}
	
	public static HashMap<String, Boolean> getAllOpenFak()
	{
		return fakOpenMap;
	}
	
	public static void putScore(String s, float o)
	{
		scoreMap.put(s, o);
	}
	
	public static float getScore(String s)
	{
		
		return scoreMap.get(s);
	}
	
	public static void setScore(int i, float a)
	{
		Integer b = i;
		scoreMap.put("fak"+b.toString(), a);
	}
	
	public static int getVotes(String s)
	{
		return voteMap.get(s);
	}
	
	public static HashMap<String, Integer> getAllVotes()
	{
		return voteMap;
	}
	
	public static void setVotes(String s, int i)
	{
		voteMap.put(s, i);
	}
	
	public static void putHelper(String s, boolean o)
	{
		inactiveHelperMap.put(s, o);
	}
	
	public static boolean getHelper(String s)
	{
		return inactiveHelperMap.get(s);
	}
	
	public static void setHelper(String s, boolean b)
	{
		inactiveHelperMap.put(s, b);
	}
	
	public static long getHelperTime(String s)
	{
		return inactiveTimeMap.get(s);
	}
	
	public static void setHelperTime(String s, long l)
	{
		inactiveTimeMap.put(s, l);
	}
	
	public static void putHangover(String s, boolean o)
	{
		hangoverMap.put(s, o);
	}
	
	public static boolean getHangover(String s)
	{
		return hangoverMap.get(s);
	}
	
	public static long getHangoverTime(String s)
	{
		return hangoverTimeMap.get(s);
	}
	
	public static void setHangoverTime(String s, long l)
	{
		hangoverTimeMap.put(s, l);
	}
}
