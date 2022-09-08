package script.behaviour;

import org.dreambot.api.methods.container.impl.Inventory;

import script.framework.Branch;
import script.utilities.API;

public class FullInvy extends Branch {
    @Override
    public boolean isValid() {
    	return Inventory.contains(API.TINDERBOX) &&
        		Inventory.count(API.MARRENTILL) >= 2;
    }
}
