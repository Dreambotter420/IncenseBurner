package script.fullInvy;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.widget.Widgets;

import script.framework.Leaf;
import script.utilities.API;
import script.utilities.Locations;
import script.utilities.Sleep;

public class EnterPortal extends Leaf {

    @Override
    public boolean isValid() {
    	return Locations.rimmington.contains(Players.localPlayer()) && !Players.localPlayer().isMoving();
    }

    @Override
    public int onLoop() {
    	API.randomAFK();
    	
    	if(API.hostName.equals("")) 
    	{
    		MethodProvider.log("No host name selected, please type \"host <hostname>\" please :-)");
    		return Sleep.Calculate(2222,2222);
    	}
    	
    	if(Widgets.getWidgetChild(162,42) != null && Widgets.getWidgetChild(162,42).isVisible()) 
    	{ //chat widget open
    		if(Widgets.getWidgetChild(162,37,0) != null && Widgets.getWidgetChild(162,37,0).isVisible() && Widgets.getWidgetChild(162,37,0).getText().contains(API.hostName)) 
    		{ //Last-used house visible, click it
    			
    			if(Widgets.getWidgetChild(162,37,0).interact()) {
    				MethodProvider.sleep(666);
    				MethodProvider.sleepUntil(() -> Locations.isInstanced(), Sleep.Calculate(10000,1111));
    				Sleep.sleep(5000, 1111);
    				return 5;
    			}
    		}
    		else 
    		{
    			Keyboard.type(API.hostName, true);
    			MethodProvider.sleep(666);
    			MethodProvider.sleepUntil(() -> Locations.isInstanced(), Sleep.Calculate(10000,1111));
    			Sleep.sleep(5000, 1111);
    		}
    	} 
    	
    	else 
    	{ //need to click house
    		if(GameObjects.closest("Portal").interact("Friend\'s house")) 
    		{
    			MethodProvider.sleep(666);
    			MethodProvider.sleepUntil(() -> Widgets.getWidgetChild(126,42) != null && Widgets.getWidgetChild(126,42).isVisible(), Sleep.Calculate(5000,1111));
    		}
    	}
    	return 5;
    }
    
    
}
