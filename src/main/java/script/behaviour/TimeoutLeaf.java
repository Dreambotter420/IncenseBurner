package script.behaviour;

import script.framework.Leaf;

/**
 * @author LostVirt
 * @created 09/11/2021 - 11:50
 * @project LCE
 */
public class TimeoutLeaf extends Leaf
{

    // If this leaf is called, that means that there is currently a tick timeout
    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public int onLoop() {
        return 0;
    }
}
