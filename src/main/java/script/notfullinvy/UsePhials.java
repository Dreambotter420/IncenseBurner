package script.notfullinvy;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.dialogues.Dialogues;
import org.dreambot.api.methods.input.Keyboard;
import org.dreambot.api.methods.interactive.NPCs;
import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.walking.impl.Walking;
import org.dreambot.api.methods.widget.Widgets;
import org.dreambot.api.wrappers.interactive.NPC;
import org.dreambot.api.wrappers.items.Item;
import script.framework.Leaf;
import script.utilities.API;
import script.utilities.Locations;
import script.utilities.Sleep;

public class UsePhials extends Leaf {

    @Override
    public boolean isValid() {
    	return Locations.rimmington.contains(Players.localPlayer());
    }

    @Override
    public int onLoop() {
    	API.randomAFK();
    	boolean useAgain = false;
    	if((Widgets.getWidgetChild(217, 5) != null && 
    			Widgets.getWidgetChild(217, 5).isVisible() && 
    			Widgets.getWidgetChild(217, 5).getText().contains("Yes please.")) ||
    			(Widgets.getWidgetChild(219, 1, 1) != null && 
    			Widgets.getWidgetChild(219, 1, 1).isVisible() && 
    			Widgets.getWidgetChild(219, 1, 1).getText().contains("Yes please.")) ||
    			(Widgets.getWidgetChild(231, 5) != null && 
    			Widgets.getWidgetChild(231, 5).isVisible() && 
    			(Widgets.getWidgetChild(231, 5).getText().contains("Do you wish me to exchange banknotes for you") ||
    			Widgets.getWidgetChild(231, 5).getText().contains("Hand me the banknotes you wish me to exchange."))))
    	{
    		
    	}
    	
    	else if(Widgets.getWidgetChild(219, 1, 1) != null && 
    			Widgets.getWidgetChild(219, 1, 1).isVisible() && 
    			Widgets.getWidgetChild(219, 1, 1).getText().contains("Exchange")) 
    	{ //have at least one option to exchange items, check for "all" and if none, choose "1"
    		//have at least one option to exchange items, check for "all" and if none, choose "1"
    		if(!Dialogues.chooseFirstOptionContaining("Exchange All:")) Keyboard.type("1",false);
    		return Sleep.Calculate(420,696);
    	}
    	//check if Phials nearby
    	NPC phials = NPCs.closest("Phials");
    	if(phials != null)
    	{
    		if(!Players.localPlayer().isInteracting(phials))
    		{
        		Item marrentillNoted = Inventory.get(API.MARRENTILL_NOTED);
        		if(marrentillNoted.useOn(phials))
    			{
    				MethodProvider.sleepUntil(Dialogues::inDialogue, () -> Players.localPlayer().isMoving(), Sleep.Calculate(2222,2222), 50);
    			}
    		}
    	}
    	else
    	{
			if(Walking.shouldWalk(6) && Walking.walk(Locations.rimmington.getCenter())) Sleep.sleep(420,1111);
    	}
    	Sleep.sleep(100,444);
    	return 5;
    }
    
}
