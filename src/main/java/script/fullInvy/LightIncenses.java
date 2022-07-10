package script.fullInvy;

import org.dreambot.api.methods.Calculations;
import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.filter.Filter;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Map;
import org.dreambot.api.methods.map.Tile;
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

    @Override
    public int onLoop() {
    	API.randomAFK();
    	boolean unlitIncense = false;
    	Filter<GameObject> incenseFilter = p -> p!=null &&p.getName().equals("Incense burner");
    	for(GameObject p : GameObjects.all(incenseFilter)) 
    	{
    		if(p.getActions()[0].equals("Light") && !p.getActions()[0].equals("Re-light"))
    		{
    			unlitIncense = true;
    			break;
    		}
    	}
    	if(unlitIncense || API.incenseTimer == null || API.incenseTimer.elapsed() > API.incenseTimerLimit)
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
    		String action = "Light";
    		if(closestBurner.hasAction("Re-light")) action = "Re-light";
    		if(closestBurner.interact(action)) {
    			MethodProvider.sleep((int) Calculations.nextGaussianRandom(1300,100));
    			API.incenseTimer = new Timer();
    			API.incenseTimerLimit = (long) Sleep.Calculate(45000,2500);
    			MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving() &&
    					!Players.localPlayer().isAnimating(), Sleep.Calculate(3000,1111));
    			Sleep.sleep(420,555);
    		}
    		action = "Light";
    		if(secondBurner.hasAction("Re-light")) action = "Re-light";
    		if(secondBurner.interact(action)) {
    			MethodProvider.sleep((int) Calculations.nextGaussianRandom(1300,100));
    			MethodProvider.sleepUntil(() -> !Players.localPlayer().isMoving() &&
    					!Players.localPlayer().isAnimating(), Sleep.Calculate(3000,1111));
    			Sleep.sleep(111,555);
    		}
    		Sleep.sleep(50,1111);
    		if(Sleep.Calculate(1, 100) < 300) {
    			Map.interact(new Tile(closestBurner.getX(),closestBurner.getY()+1,Players.localPlayer().getZ()));
    		}
    	}
    	Sleep.sleep(500,1111);
    	return 5;
    }


   
}
