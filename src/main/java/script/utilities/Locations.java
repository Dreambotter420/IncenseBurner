package script.utilities;

import org.dreambot.api.methods.map.Area;

import script.p;

public class Locations {

	public static final Area rimmington = new Area(2944, 3209, 2960, 3229, 0);
	public static final int edgeOfTheWorldX = 3904;
	public static boolean isInstanced() {
		if(p.l.getX() >= edgeOfTheWorldX) return true;
		return false;
	}
	
	
}
