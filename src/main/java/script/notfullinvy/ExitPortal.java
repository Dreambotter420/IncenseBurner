package script.notfullinvy;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.Inventory;
import org.dreambot.api.methods.interactive.GameObjects;

import script.Main;
import script.p;
import script.framework.Leaf;
import script.utilities.API;
import script.utilities.Locations;
import script.utilities.Sleep;

public class ExitPortal extends Leaf {

    @Override
    public boolean isValid() {
    	return Locations.isInstanced();
    }

    @Override
    public int onLoop() {
    	
    	Main.currentTask = "~Exiting POH~";
    	API.randomAFK();
    	if(p.l.isMoving()) return Sleep.Calculate(111,420);
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
    		MethodProvider.sleepUntil(()-> Locations.rimmington.contains(p.l), Sleep.Calculate(5000,1111));
    		Sleep.sleep(55,555);
    	}
    	return 5;
    }
    
}
