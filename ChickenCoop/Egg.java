import info.gridworld.actor.Actor;

import java.awt.Color;

public class Egg extends Actor {
	
	private int steps;
	
	public Egg() {
		steps = 0;
		setColor(Color.WHITE);
	}
	
	public void act() {
		if (steps < 45) {
			int red = getColor().getRed();
			int green = getColor().getGreen();
			int blue = getColor().getBlue();
			setColor(new Color(red, green, blue));
		} else if (steps == 45) {
			setColor(Color.RED);
		} else if (steps == 50) {
			Chicken chicken = new Chicken();
			chicken.putSelfInGrid(getGrid(), getLocation());
		}
		
		steps++;
	}
}
