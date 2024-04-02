import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class Chicken extends Critter {
	
	private int age; // amount of time steps passed
	private final int MIDDLE_AGE = 200;
	private final int ELDERLY = 280;
	private final int DECEASED = 300;
	private final double DARKENING_FACTOR = 0.02;
	
	public Chicken() {
		setColor(Color.WHITE);
	}
	
	/** The Chicken does not process any Actors, so it will not get any Actors */
	public ArrayList<Actor> getActors() {
		return new ArrayList<Actor>(0);
	}
	
	/** 
	 * Gives a list of move location candidates depending on the age of the 
	 * Chicken
	 * @return set of all possible move locations
	 */
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = null;
		Location nextLoc = getLocation().getAdjacentLocation(getDirection());
		if (age < DECEASED) {
			locs = new ArrayList<Location>();
			if (age < MIDDLE_AGE) {
				locs.add(getLocation());
				if (getGrid().isValid(nextLoc) && getGrid().get(nextLoc) == null)
					locs.add(nextLoc);
			} else if (MIDDLE_AGE <= age && age < ELDERLY) {
				if (age % 2 == 1) {
					locs.add(getLocation());
					if (getGrid().isValid(nextLoc) && getGrid().get(nextLoc) == null)
						locs.add(nextLoc);
				} else {
					locs.add(getLocation());
				}
			} else if (ELDERLY <= age) {
				if (age % 4 == 3) {
					locs.add(getLocation());
					if (getGrid().isValid(nextLoc) && getGrid().get(nextLoc) == null)
						locs.add(nextLoc);
				} else {
					locs.add(getLocation());
				}
			}
		} 
		
		return locs;
	}
	
	/**
	 * Selects the move location that will be chosen on the next turn
	 */
	public Location selectMoveLocation(ArrayList<Location> locs) {
		Location selected = null;
		int index = -1;
		if (locs != null) {
			if (locs.size() == 2) {
				index = (int)(2 * Math.random());
			} else {
				index = 0;
			}
			selected = locs.get(index);
		}
		
		return selected;
	}
	
	/**
	 * Makes a move to the location. If the location is null, a Tombstone will
	 * be put into the grid. If it stays in the same location, then it 
	 * will turn its age allows it to turn. If the location is different, it 
	 * will move to that location and lay an egg if its young. If the Chicken is
	 * elderly, it will become gray. 
	 */
	public void makeMove(Location loc) {
		Location currentLoc = getLocation();
		Tombstone tomb = null;
		Egg egg = null;
		int turnCount = -1;
		int createEgg = -1;
		
		if (loc == null) {
			tomb = new Tombstone();
			tomb.putSelfInGrid(getGrid(), currentLoc);
		} else if (loc.equals(currentLoc)) {
			if (canTurn()) {
				turnCount = (int)(8 * Math.random());
				setDirection(getDirection() + turnCount * Location.HALF_RIGHT);
			}
		} else {
			moveTo(loc);
			if (age < MIDDLE_AGE) {
				createEgg = (int)(20 * Math.random());
				if (createEgg == 0) {
					egg = new Egg();
					egg.putSelfInGrid(getGrid(), currentLoc);
				}
			}
		}
		
		if (age >= ELDERLY) {
			darken();
		}
		
		age++;
	}
	
	/** 
	 * Returns whether the Chicken can turn for a particular age value
	 */
	private boolean canTurn() {
		boolean canTurn = false;
		if (age < MIDDLE_AGE) {
			canTurn = true;
		} else if (MIDDLE_AGE <= age && age < ELDERLY) {
			if (age % 2 == 1) {
				canTurn = true;
			}
		} else if (ELDERLY <= age) {
			if (age % 4 == 3) {
				canTurn = true;
			}
		}
		
		return canTurn;
	}
	
	/** Darkens the color of the Chicken. Only called when Chicken is elderly */
	private void darken() {
		Color color = getColor();
		int red = (int) (color.getRed() * (1 - DARKENING_FACTOR));
		int green = (int) (color.getGreen() * (1 - DARKENING_FACTOR));
		int blue = (int) (color.getBlue() * (1 - DARKENING_FACTOR));
		setColor(new Color(red, green, blue));
	}
	
	/*
	/** 
	 * This chooses whether the chicken will make a movement or if it will 
	 * not move at all. 
	 * @param loc Location where the move ends
	 
	public void makeMove(Location loc) {
		if (age < MIDDLE_AGE) {
			makeMovement(loc);
		} else if (MIDDLE_AGE <= age && age < ELDERLY) {
			if (age % 2 == 1) {
				makeMovement(loc);
			}
		} else if (ELDERLY <= age && age < DECEASED) {
			darken();
			if (age % 4 == 3) {
				makeMovement(loc);
			}
		} else {
			tomb = new Tombstone();
			tomb.putSelfInGrid(getGrid(), getLocation());
		}
		age++;
	}
	
	// the chicken makes a movement on the appropriate turn
	private void makeMovement(Location loc) {
		boolean willMove = currentLoc.equals(loc);
		if (willMove) {
			Location currentLoc = getLocation();
			moveTo(loc);
			int layEgg = (int)(20 * Math.random());
			if (layEgg == 0) {
				egg = new Egg();
				egg.putSelfInGrid(getGrid(), currentLoc);
			}
		} else {
			turnCount = (int)(8 * Math.random());
			setDirection(getDirection() + turnCount * Location.HALF_RIGHT);
		}
	}
	*/
}
