package script.utilities;

import org.dreambot.api.methods.interactive.Players;
import org.dreambot.api.methods.map.Area;

public class Locations {

	public static final Area rimmington = new Area(2944, 3209, 2960, 3229, 0);
	public static final int edgeOfTheWorldX = 3904;
	public static boolean isInstanced() {
		if(Players.localPlayer().getX() >= edgeOfTheWorldX) return true;
		return false;
	}
	
	
}
