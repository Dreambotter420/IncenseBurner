package script.utilities;

import java.util.Random;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.utilities.Timer;



public class API {
	public static String currentBranch = "";
	public static String currentLeaf = "";
	
	/**
	 * Configuration:
	 * all names are converted to lowercase in script so casing doesn't matter
	 * hostName is host house to enter (does not support random PoHs with altars)
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
	public static int randomWorld = 0;
	public static boolean initialized = false;
	public static Random rand2 = new Random();
	public static int minute = 0;
	public static Timer runTimer;
	public static Timer incenseTimer;
	public static long incenseTimerLimit;
	public static void randomAFK()
	{
		int tmp = API.rand2.nextInt(40000);
		if(tmp < 2)  
		{
			MethodProvider.logInfo("AFK: 0.001% chance, max 240s");
			Sleep.sleep(50,10000);
		}
		else if(tmp < 6)  
		{
			MethodProvider.logInfo("AFK: 0.003% chance, max 120s");
			Sleep.sleep(50,5000);
		}
		else if(tmp < 25)
		{
			MethodProvider.logInfo("AFK: 0.095% chance, max 40s");
			Sleep.sleep(50,3000);
		}
		else if(tmp < 150)  
		{
			MethodProvider.logInfo("AFK: .625% chance, max 20s");
			Sleep.sleep(50,2000);
		}
		else if(tmp < 1000)  
		{
			MethodProvider.logInfo("AFK: 4.25% chance, max 6.0s");
			Sleep.sleep(50,1200);
		}
		else if(tmp < 3000)  
		{
			MethodProvider.logInfo("AFK: 10.0% chance, max 3.2");
			Sleep.sleep(50,600);
		}
	}
}
