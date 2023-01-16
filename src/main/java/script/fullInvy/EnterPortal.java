package script.fullInvy;

import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.utilities.Logger;

import script.Main;
import script.p;
import script.framework.Leaf;
import script.utilities.API;
import script.utilities.Locations;
import script.utilities.Sleep;

public class EnterPortal extends Leaf {

    @Override
    public boolean isValid() {
    	return Locations.rimmington.contains(p.l) && !p.l.isMoving();
    }

    @Override
    public int onLoop() {
    	Main.currentTask = "~Entering portal~";
    	API.randomAFK();
    	
    	if(API.hostName.equals("")) 
    	{
        	Main.currentTask = "WAITING ON USER INPUT TO SPECIFY HOSTNAME";
    		Logger.log("No host name selected, please type \"host <hostname>\" please :-)");
    		return Sleep.calculate(2222,2222);
    	}
    	
    	if(Widgets.getWidgetChild(162,42) != null && Widgets.getWidgetChild(162,42).isVisible()) 
    	{ //chat widget open
    		Main.currentTask = "~Using house portal chat interface~";
    		if(Widgets.getWidgetChild(162,37,0) != null && Widgets.getWidgetChild(162,37,0).isVisible() && Widgets.getWidgetChild(162,37,0).getText().contains(API.hostName)) 
    		{ //Last-used house visible, click it
    			
    			if(Widgets.getWidgetChild(162,37,0).interact()) {
    				org.dreambot.api.utilities.Sleep.sleep(666);
    				org.dreambot.api.utilities.Sleep.sleepUntil(() -> Locations.isInstanced(), Sleep.calculate(10000,1111));
    				Sleep.sleep(5000, 1111);
    				return 5;
    			}
    		}
    		else 
    		{
    			Keyboard.type(API.hostName, true);
    			org.dreambot.api.utilities.Sleep.sleep(666);
    			org.dreambot.api.utilities.Sleep.sleepUntil(() -> Locations.isInstanced(), Sleep.calculate(10000,1111));
    			Sleep.sleep(5000, 1111);
    		}
    	} 
    	
    	else 
    	{ //need to click house
    		Main.currentTask = "~Clicking \'Friend\'s house\' on Portal~";
    		if(GameObjects.closest("Portal").interact("Friend\'s house")) 
    		{
    			org.dreambot.api.utilities.Sleep.sleep(666);
    			org.dreambot.api.utilities.Sleep.sleepUntil(() -> Widgets.getWidgetChild(126,42) != null && Widgets.getWidgetChild(126,42).isVisible(), Sleep.calculate(5000,1111));
    		}
    	}
    	return 5;
    }
    
    
}
