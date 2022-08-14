package script.fullInvy;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.wrappers.interactive.GameObject;

import script.framework.Leaf;
import script.utilities.API;
import script.utilities.Locations;
import script.utilities.Sleep;

public class LightIncenses extends Leaf {

    @Override
    public boolean isValid() {
    	return Locations.isInstanced();
    }
    private static Tile burner1 = null;
    private static Tile burner2 = null;
    private static boolean lit1 = false;
    private static boolean lit2 = false;
    @Override
    public int onLoop() {
    	API.randomAFK();
    	
    	//first check for any unlit incenses
    	boolean unlitIncense = false;
    	Filter<GameObject> incenseFilter = p -> p != null && p.getName().equals("Incense burner");
    	for(GameObject p : GameObjects.all(incenseFilter)) 
    	{
    		if(p.getActions()[0].equals("Light") && !p.getActions()[0].equals("Re-light"))
    		{
    			unlitIncense = true;
    			break;
    		}
    	}
    	if(API.incenseTimer != null && API.incenseTimer.finished()) 
		{
			lit1 = false;
			lit2 = false;
		}
    	//proceed to light burners if incenseTimer is up or have any unlit burners
    	if(unlitIncense || !lit1 || !lit2)
    	{
    		
    		//first establish tile locations of all burners (labeling them 1 / 2 for the duration of being in the house)
    		if(burner1 == null || burner2 == null)
    		{
    			GameObject closestBurner = GameObjects.closest("Incense burner");
        		GameObject secondBurner = null;
        		for(GameObject burner : GameObjects.all(incenseFilter)) 
        		{
        			if(!burner.getTile().equals(closestBurner.getTile())) 
        			{
        				secondBurner = burner;
        				break;
        			}
        		}
        		if(closestBurner == null || secondBurner == null) 
        		{
        			MethodProvider.log("Not a pair of incenses found in instanced area (house)!");
        			return Sleep.Calculate(5555,5555);
        		}
        		burner1 = closestBurner.getTile();
        		burner2 = secondBurner.getTile();
        		return Sleep.Calculate(69,69);
    		}
    		
    		GameObject closestBurner = GameObjects.closest("Incense burner");
    		GameObject secondBurner = null;
    		for(GameObject burner : GameObjects.all(incenseFilter)) 
    		{
    			if(!burner.getTile().equals(closestBurner.getTile())) 
    			{
    				secondBurner = burner;
    				break;
    			}
    		}
    		if(closestBurner == null || secondBurner == null) 
    		{
    			MethodProvider.log("Not a pair of incenses found in instanced area (house)!");
    			return Sleep.Calculate(5555,5555);
    		}
    		
    		if(!lit1)
    		{
    			String action = "Light";
        		if(closestBurner.hasAction("Re-light")) action = "Re-light";
        		
        		if(closestBurner.interact(action)) {
        			MethodProvider.sleep((int) Calculations.nextGaussianRandom(1300,100));
        			long maxTimeLimit = ((long) ((Skills.getRealLevel(Skill.FIREMAKING) + 200) * 0.6 * 1000)) - 10000;
        			long randTimerLimit = (long) Calculations.nextGaussianRandom(((Skills.getRealLevel(Skill.FIREMAKING)/2) + 200) * 0.6 * 1000, Skills.getRealLevel(Skill.FIREMAKING)/3);
        			if(randTimerLimit >= maxTimeLimit) randTimerLimit = maxTimeLimit;
        				
        			API.incenseTimer = new Timer(randTimerLimit);
        			MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving() &&
        					!Players.localPlayer().isAnimating(), Sleep.Calculate(3000,1111));
        			Sleep.sleep(420,555);
        			lit1 = true;
        		}
        		return Sleep.Calculate(420,696);
    		}
    		
    		if(!lit2)
    		{
    			String action = "Light";
        		if(secondBurner.hasAction("Re-light")) action = "Re-light";
        		if(secondBurner.interact(action)) {
        			lit2 = true;
        			MethodProvider.sleep((int) Calculations.nextGaussianRandom(1300,100));
        			MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving() &&
        					!Players.localPlayer().isAnimating(), Sleep.Calculate(3000,1111));
        			Sleep.sleep(111,555);
        		}
        		Sleep.sleep(50,1111);
        		return Sleep.Calculate(420,696);
    		}
    		
    	}
    	
    	Tile standTile = new Tile(burner1.getX(),burner1.getY()+1,Players.localPlayer().getZ());
    	if(!Players.localPlayer().getTile().equals(standTile)) Map.interact(standTile);
    	Sleep.sleep(500,1111);
    	return 5;
    }


   
}
