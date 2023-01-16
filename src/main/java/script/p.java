package script;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.wrappers.interactive.Player;

import script.framework.Leaf;
import script.utilities.Sleep;

public class p extends Leaf{
	public static Player l = null;
	@Override
	public boolean isValid() {
		return l == null || !l.exists();
	}

	@Override
	public int onLoop() {
		l = Players.getLocal();
		return Sleep.calculate(69,111);
	}
	
}
