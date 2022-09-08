package script.behaviour;

import java.time.LocalTime;

import org.dreambot.api.methods.MethodProvider;
import org.dreambot.api.methods.container.impl.bank.Bank;
import org.dreambot.api.methods.widget.Widgets;

import script.p;
import script.framework.Leaf;
import script.utilities.API;
import script.utilities.Sleep;

public class Initialize extends Leaf {

    @Override
    public boolean isValid() {
        return !API.initialized;
    }

    @Override
    public int onLoop() {
    	if(!Bank.isOpen() && (Widgets.getWidgetChild(192,4) == null || !Widgets.getWidgetChild(192,4).isVisible()))
    	{
    		Widgets.closeAll(); // only close everything if bank / deposit box are NOT open
    	}
    	//randomize psuedorandom seed based on in-game account name  & time of day then set sleep modification factor (0-4x)
    	if(p.l != null && !p.l.getName().isEmpty())
		{
			String name = p.l.getName();
			int mult = 1;
			int sum = 0;
		    for(int i = 0; i < name.length() - 1; i++)
		    {
		    	sum += name.codePointAt(i) * mult;
				mult += 2;
		    }
			API.rand2.setSeed(sum + LocalTime.now().getNano());
		}
    	Sleep.initSleepMod = 1.2 + (API.rand2.nextDouble()/1.25);
    	Sleep.initSleepMod = Sleep.initSleepMod * Sleep.initSleepMod;
    	//all initial randomizations that depend on new random seed go here
    	API.initialized = true;
    	MethodProvider.log("Initialized");
        return 5;
    }
}
