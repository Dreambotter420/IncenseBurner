package script;

import org.dreambot.api.script.AbstractScript;
import org.dreambot.api.script.Category;
import org.dreambot.api.script.ScriptManifest;
import org.dreambot.api.script.listener.ChatListener;
import org.dreambot.api.utilities.Logger;
import org.dreambot.api.utilities.Timer;
import org.dreambot.api.wrappers.widgets.message.Message;

import script.behaviour.FullInvy;
import script.behaviour.Initialize;
import script.behaviour.NotFullInvy;
import script.behaviour.TimeoutLeaf;
import script.behaviour.WaitForLogged_N_Loaded;
import script.framework.Tree;
import script.fullInvy.EnterPortal;
import script.fullInvy.LightIncenses;
import script.notfullinvy.ExitPortal;
import script.notfullinvy.UsePhials;
import script.paint.CustomPaint;
import script.paint.PaintInfo;
import script.utilities.*;

import java.awt.*;
import java.time.LocalDateTime;

@ScriptManifest(name = "IncenseLighter", author = "Dreambotter420", version = 1.2, category = Category.MISC)
public class Main extends AbstractScript implements PaintInfo, ChatListener {
	@Override
	public void onStart() {
		Sleep.dt = LocalDateTime.now();
		instantiateTree();
	}

	@Override
	public void onStart(String[] args) {
		for (String arg : args) {
			API.hostName = arg;
		}
		onStart();
	}

	private void instantiateTree() {
		tree.addBranches(new WaitForLogged_N_Loaded(), new p(), new Initialize(),
				new FullInvy().addLeafs(new EnterPortal(), new LightIncenses()),
				new NotFullInvy().addLeafs(new ExitPortal(), new UsePhials()), new TimeoutLeaf());
	}

	public final Tree tree = new Tree();

	@Override
	public int onLoop() {
		return tree.onLoop();
	}

	@Override
	public void onMessage(Message msg) {
		if (msg.getUsername().contains(p.l.getName())) {
			Logger.log("See our own msg: " + msg.getMessage());
			if (msg.getMessage().toLowerCase().startsWith("host ")) {
				API.hostName = msg.getMessage().toLowerCase().split(" ", 2)[1];
				Logger.log("Set hostname: " + API.hostName);
			}
		}
		if (msg.getMessage().contains("That player is offline, or has privacy mode enabled")) {
			Logger.log("Removing host name due to offline or privacy mode: " + API.hostName);
			API.hostName = "";
		}
	}

	// Our paint info
	// Add new lines to the paint here
	public String[] getPaintInfo() {
		return new String[] { getManifest().name() + " V" + getManifest().version() + " by Dreambotter420 ^_^",
				"Current task: " + currentTask,
				API.incenseTimer != null ? "Time since last light " + Timer.formatTime(API.incenseTimer.elapsed())
						: "No incenses lit",
				API.incenseTimer != null ? "Time until next light " + Timer.formatTime(API.incenseTimer.remaining())
						: "No incenses lit" };
	}

	public static String currentTask = "";

	// Instantiate the paint object. This can be customized to your liking.
	private final CustomPaint CUSTOM_PAINT = new CustomPaint(this, CustomPaint.PaintLocations.BOTTOM_LEFT_PLAY_SCREEN,
			new Color[] { new Color(255, 251, 255) }, "Trebuchet MS", new Color[] { new Color(50, 50, 50, 175) },
			new Color[] { new Color(28, 28, 29) }, 1, false, 5, 3, 0);
	private final RenderingHints aa = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);

	@Override
	public void onPaint(Graphics2D graphics2D) {
		// Set the rendering hints
		graphics2D.setRenderingHints(aa);
		// Draw the custom paint
		CUSTOM_PAINT.paint(graphics2D);
	}
}
