package script.idling;

import script.framework.Leaf;
import script.utilities.Sleep;

public class Idling extends Leaf {

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
    	Sleep.sleep(500,2222);
        return 5;
    }
}
