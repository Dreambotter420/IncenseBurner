package script.fullInvy;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.map.Tile;
import org.dreambot.api.methods.skills.Skill;
import org.dreambot.api.methods.skills.Skills;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.wrappers.interactive.GameObject;

import script.Main;
import script.p;
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
        			Logger.log("Not a pair of incenses found in instanced area (house)!");
        			return Sleep.calculate(5555,5555);
        		}
        		burner1 = closestBurner.getTile();
        		burner2 = secondBurner.getTile();
        		return Sleep.calculate(69,69);
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
    			Logger.log("Not a pair of incenses found in instanced area (house)!");
    			return Sleep.calculate(5555,5555);
    		}
    		
    		if(!lit1)
    		{
    			Main.currentTask = "~Lighting incense #1~";
    			String action = "Light";
        		if(closestBurner.hasAction("Re-light")) action = "Re-light";
        		
        		if(closestBurner.interact(action)) {
        			org.dreambot.api.utilities.Sleep.sleep((int) Calculations.nextGaussianRandom(1300,100));
        			long maxTimeLimit = ((long) ((Skills.getRealLevel(Skill.FIREMAKING) + 200) * 0.6 * 1000)) - 10000;
        			long randTimerLimit = (long) Calculations.nextGaussianRandom(((Skills.getRealLevel(Skill.FIREMAKING)/2) + 200) * 0.6 * 1000, Skills.getRealLevel(Skill.FIREMAKING)/3);
        			if(randTimerLimit >= maxTimeLimit) randTimerLimit = maxTimeLimit;
        				
        			API.incenseTimer = new Timer(randTimerLimit);
        			org.dreambot.api.utilities.Sleep.sleepUntil(() -> !p.l.isMoving() &&
        					!p.l.isAnimating(), Sleep.calculate(3000,1111));
        			Sleep.sleep(420,555);
        			lit1 = true;
        		}
        		return Sleep.calculate(420,696);
    		}
    		
    		if(!lit2)
    		{
    			Main.currentTask = "~Lighting incense #2~";
    			String action = "Light";
        		if(secondBurner.hasAction("Re-light")) action = "Re-light";
        		if(secondBurner.interact(action)) {
        			lit2 = true;
        			org.dreambot.api.utilities.Sleep.sleep((int) Calculations.nextGaussianRandom(1300,100));
        			org.dreambot.api.utilities.Sleep.sleepUntil(() -> !p.l.isMoving() &&
        					!p.l.isAnimating(), Sleep.calculate(3000,1111));
        			Sleep.sleep(111,555);
        		}
        		Sleep.sleep(50,1111);
        		return Sleep.calculate(420,696);
    		}
    		
    	}
    	//time to search for nearest rug and portal relative to altar, do maths, stand on tile next to altar that is both closest to rug and portal, but rug 1st priority
    	//to stand on top of usual players finding least distance from portal -> altar
    	GameObject altar = GameObjects.closest(g-> g!=null && g.getName().equals("Altar") && g.hasAction("Pray"));
    	int portalDistFromTile1 = 99;
    	int portalDistFromTile2 = 99;
    	Tile carpetTile1 = null;
    	Tile carpetTile2 = null;
    	Tile standTile = new Tile(burner1.getX(),burner1.getY()+1,p.l.getZ());
    	boolean tile2 = false;
    	for(Tile t : altar.getObjectTiles())
    	{
    		GameObject closestRug = GameObjects.closest(g -> g!=null && g.getName().equals("Rug") && g.distance(t) <= 3, t);
    		//revert to default standtile if no rugs found
    		if(closestRug == null)
    		{
    			if(!p.l.getTile().equals(standTile))
    	    	{
    	    		Main.currentTask = "~Walking to idle tile~";
    	    		if(p.l.isMoving()) return Sleep.calculate(500,1111);
    	    		Walking.clickTileOnMinimap(standTile);
    	    	}
	    		return Sleep.calculate(500,1111);
    		}
    		if(!tile2) 
    		{
    			carpetTile1 = closestRug.getTile();
    			tile2 = true;
    		}
    		else carpetTile2 = closestRug.getTile();
    	}
    	GameObject portal = GameObjects.closest(g -> g!=null && g.getName().equals("Portal") && 
    			g.hasAction("Enter") && 
    			g.hasAction("Lock") && 
    			g.hasAction("Remove board advert"));

    	portalDistFromTile1 =(int) portal.distance(carpetTile1);
    	portalDistFromTile2 =(int) portal.distance(carpetTile2);
    	if(portalDistFromTile1 == portalDistFromTile2) standTile = carpetTile1;
    	else if(portalDistFromTile1 >= portalDistFromTile2) standTile = carpetTile2;
    	else standTile = carpetTile1;
    	if(!p.l.getTile().equals(standTile))
    	{
    		Main.currentTask = "~Walking to idle tile~";
    		if(p.l.isMoving()) return Sleep.calculate(500,1111);
    		Walking.clickTileOnMinimap(standTile);
    		return Sleep.calculate(500,1111);
    	}
    	Main.currentTask = "~Sitting on ass~";
    	Sleep.sleep(500,1111);
    	return 5;
    }


   
}
