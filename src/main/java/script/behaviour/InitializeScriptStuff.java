package script.behaviour;

import org.dreambot.api.Client;

import script.framework.Root;
import script.utilities.API;

public class InitializeScriptStuff extends Root {
    @Override
    public boolean isValid() {
    	return Client.isLoggedIn() && !API.initialized;
    }

}
