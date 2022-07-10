package script.behaviour;

import org.dreambot.api.Client;
import org.dreambot.api.methods.container.impl.Inventory;

import script.framework.Root;
import script.utilities.API;

public class FullInvy extends Root {
    @Override
    public boolean isValid() {
    	return Client.isLoggedIn() && 
        		Inventory.contains(API.TINDERBOX) &&
        		Inventory.count(API.MARRENTILL) >= 2;
    }
}
