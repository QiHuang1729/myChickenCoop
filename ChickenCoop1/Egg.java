import info.gridworld.actor.Actor;

import java.awt.Color;

public class Egg extends Actor {
	
	private int steps;
	private final double DARKENING_FACTOR = 0.02;
	
	public Egg() {
		steps = 0;
		setColor(Color.WHITE);
	}
	
	public void act() {
		if (steps < 45) {
			Color color = getColor();
			int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
			int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
			int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
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
