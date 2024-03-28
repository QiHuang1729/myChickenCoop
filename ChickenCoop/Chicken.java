import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.awt.Color;

public class Chicken extends Critter {
	
	private int age; // amount of time steps passed
	private final int MIDDLE_AGE = 200;
	private final int ELDERLY = 280;
	private final int DECEASED = 300;
	
	public Chicken() {
		setColor(Color.WHITE);
	}
	
	public ArrayList<Location> getMoveLocations() {
		ArrayList<Location> locs = new ArrayList<>();
		Location currentLoc = getLocation();
		Location nextLoc = currentLoc.getAdjacentLocation(getDirection());
		locs.add(currentLoc);
		if (getGrid().isValid(nextLoc))
			locs.add(nextLoc);
		return locs;
	}
	
	/** 
	 * If the Chicken is alive and will move, it either moves to a new 
	 * position or turns in one of the eight possible directions. If it 
	 * is dead, it is replaced with a Tombstone.
	 * @param loc Location where the move ends
	 */
	public void makeMove(Location loc) {
		Location currentLoc = getLocation();
		int turnCount = -1;
		int layEgg = -1;
		Egg egg = null;
		Tombstone tomb = null;
		
		if (age < DECEASED) {
			
			if (willMove()) {
				if (loc.equals(getLocation())) {
					turnCount = (int)(8 * Math.random());
					setDirection(getDirection() + turnCount * Location.HALF_RIGHT);
				} else {
					moveTo(loc);
					layEgg = (int)(20 * Math.random());
					if (layEgg == 0) {
						egg = new Egg();
						egg.putSelfInGrid(getGrid(), currentLoc);
					}
				}
			}
			age++;
		} else {
			tomb = new Tombstone();
			tomb.putSelfInGrid(getGrid(), currentLoc);
		}
	}
	
	private int findPhase() {
		
	}
	
	private void processSelf(int phase) {
		
	}
	
	private void willMove(int phase) {
		
	}
	
	/** 
	 * return if the Chicken will move based on its current age
	 */
	private boolean processAge() {
		boolean canMove = false;
		
		if (age < MIDDLE_AGE) {
			canMove = true;
		} else if (MIDDLE_AGE <= age && age < ELDERLY) {
			if (MIDDLE_AGE == age) {
			}
			// true if age is odd, or current turn is even 
			if (age % 2 == 1) {
				canMove = true;
			} else {
				canMove = false;
			}
		} else if (ELDERLY <= age && age < DECEASED) {
			// true if age is 3 mod 4, or current turn is a multiple of 4
			int red = getColor().getRed();
			int green = getColor().getGreen();
			int blue = getColor().getBlue();
			setColor(new Color(red, green, blue));
			if (age % 4 == 3) {
				canMove = true;
			} else {
				canMove = false;
			}
		} else {
			canMove = false;
		}
		
		return canMove;
	}
}
