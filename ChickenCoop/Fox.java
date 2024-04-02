import info.gridworld.actor.Critter;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class Fox extends Critter {
	
	private int hunger;
	private boolean napping;
	
	public Fox() {
		setColor(null);
		napping = false;
		hunger = 0;
	}
	
	/** 
	 * Gets all the Chickens next to this Fox that this Fox wants to eat.
	 * @return An ArrayList of all adjacent Chickens the Fox is capable 
	 * of eating.
	 */
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> result = new ArrayList<>();
		int index = 0;
		if (!napping) {
			result = getGrid().getNeighbors(getLocation());
			while (index < result.size()) {
				if (!(result.get(index) instanceof Chicken)) {
					result.remove(index);
				} else {
					index++;
				}
			}
		}
		
		return result;
	}
	
	/** 
	 * Selects a random Actor from the actors array and removes (eats) it. 
	 * The getActors method in this class guarantees all of the Actors 
	 * are Chickens.
	 * @param actors The actors which will be processed. 
	 */
	public void processActors(ArrayList<Actor> actors) {
		if (actors.size() != 0) {
			int index = (int) (actors.size() * Math.random());
			Actor actor = actors.get(index);
			actor.removeSelfFromGrid();
			napping = true;
			hunger = 0;
		}
	}
	
	/**
	 * Gets all empty neighboring locations
	 * @return an ArrayList of all neighboring available locations
	 */
	public ArrayList<Location> getMoveLocations() {
		return getGrid().getEmptyAdjacentLocations(getLocation());
	}
	
	public Location selectMoveLocation(ArrayList<Location> locs) {
		Location next = null;
		
		ArrayList<Location> occupied = getGrid().getOccupiedLocations();
		Location currentLoc = null;
		
		double distance = 10000;
		Location target = null;
		int direction = -1;
		
		ArrayList<Location> candidates = new ArrayList<>();
		int choice = -1;
		
		if (napping) {
			next = getLocation();
		} else if (hunger >= 20) {
			next = null;
		} else {
			for (int i = 0; i < occupied.size(); i++) {
				currentLoc = occupied.get(i);
				if (getGrid().get(currentLoc) instanceof Chicken &&	
					distance(currentLoc) < distance) {
					distance = distance(currentLoc);
					target = currentLoc;
					candidates = new ArrayList<>();
					candidates.add(target);
				} else if (getGrid().get(currentLoc) instanceof Chicken 
					&& distance(currentLoc) == distance) {
					candidates.add(currentLoc);
				}
			}
			
			if (distance == 10000) {
				next = super.selectMoveLocation(locs);
			} else {
				choice = (int)(candidates.size() * Math.random());
				direction =
				getLocation().getDirectionToward(candidates.get(choice));
				next = getLocation().getAdjacentLocation(direction);
				if (!getGrid().isValid(next) || getGrid().get(next) 
																!= null) {
					next = super.selectMoveLocation(locs);
				}
			}
		}
		
		return next;
	}
	
	/** Moves to loc. If loc is null, then the fox is dead, so it is 
	 * replaced a tombstone. If loc is the same as the current location,
	 * the fox is either napping or has nowhere to move. Else, the 
	 * location is valid and is available for moving.
	 * @param loc The location where the Fox ends
	 */
	public void makeMove(Location loc) {
		if (loc == null) {
			Tombstone tomb = new Tombstone();
			tomb.putSelfInGrid(getGrid(), getLocation());
		} else if (loc.equals(getLocation())) {
			if (!napping) {
				int random = (int)(8 * Math.random());
				setDirection(getDirection() + random * 
					Location.HALF_RIGHT);
			}
		} else {
			int dir = getLocation().getDirectionToward(loc);
			setDirection(dir);
			moveTo(loc);
		}
	}
	
	/** Calculates the distance from the Fox to another location using 
	 * the distance formula.
	 * @param loc The other location
	 * @return the distance to the other location
	 */
	private double distance(Location loc) {
		int x1 = getLocation().getCol();
		int x2 = loc.getCol();
		int y1 = getLocation().getRow();
		int y2 = loc.getCol();
		
		return Math.sqrt((x2 - x1) ^ 2 + (y2 - y1) ^ 2);
	}
}
