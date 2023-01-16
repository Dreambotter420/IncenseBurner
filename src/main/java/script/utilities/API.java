package script.utilities;

import java.util.Random;

import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Timer;

import script.Main;



public class API {
	
	/**
	 * Configuration:
	 * all names are converted to lowercase in script so letter casing doesn't matter
	 * hostname is username of host house to enter (does not support random PoHs with altars)
	 * enter in chat "host <hostname>" can be lowercase or not
	 */
	
	public static String hostName = "";
	
	
	public static final int COINS = 995;
	public static final int MARRENTILL = 251;
	public static final int MARRENTILL_NOTED = 252;
	public static final int TINDERBOX = 590;
	public static boolean resendTrade = false;
	public static boolean idle = false;
	public static int randomRun = 0;
	public static boolean initialized = false;
	public static Random rand2 = new Random();
	public static int minute = 0;
	public static Timer runTimer;
	public static Timer incenseTimer;
	public static void randomAFK()
	{
		int tmp = API.rand2.nextInt(40000);
		if(tmp < 2)  
		{
			Logger.log("AFK: 0.001% chance, max 240s");
			Main.currentTask = "~AFK~";
			Sleep.sleep(50,10000);
		}
		else if(tmp < 6)  
		{
			Logger.log("AFK: 0.003% chance, max 120s");
			Main.currentTask = "~AFK~";
			Sleep.sleep(50,5000);
		}
		else if(tmp < 25)
		{
			Logger.log("AFK: 0.095% chance, max 40s");
			Main.currentTask = "~AFK~";
			Sleep.sleep(50,3000);
		}
		else if(tmp < 150)  
		{
			Logger.log("AFK: .625% chance, max 20s");
			Main.currentTask = "~AFK~";
			Sleep.sleep(50,2000);
		}
		else if(tmp < 1000)  
		{
			Logger.log("AFK: 4.25% chance, max 6.0s");
			Main.currentTask = "~AFK~";
			Sleep.sleep(50,1200);
		}
		else if(tmp < 3000)  
		{
			Logger.log("AFK: 10.0% chance, max 3.2");
			Main.currentTask = "~AFK~";
			Sleep.sleep(50,600);
		}
	}
}
