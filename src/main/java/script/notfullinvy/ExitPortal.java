package script.notfullinvy;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;
import org.dreambot.api.methods.interactive.Players;

import script.framework.Leaf;
import script.utilities.API;
import script.utilities.Locations;
import script.utilities.Sleep;

public class ExitPortal extends Leaf {

    @Override
    public boolean isValid() {
    	return Locations.isInstanced() && !Players.localPlayer().isMoving();
    }

    @Override
    public int onLoop() {
    	API.randomAFK();
    	if(GameObjects.closest("Portal").interact("Enter")) 
    	{
    		Sleep.sleep(55,696);
    		if(Sleep.Calculate(1, 100) < 300) 
    		{
    			if(Inventory.isItemSelected() && Inventory.getSelectedItemId() != API.MARRENTILL_NOTED) 
    			{
    				Inventory.deselect();
    				Sleep.sleep(10,50);
    			}
    		}
    		MethodProvider.sleepUntil(()-> Locations.rimmington.contains(Players.localPlayer()), Sleep.Calculate(5000,1111));
    		Sleep.sleep(55,555);
    	}
    	return 5;
    }
    
}
