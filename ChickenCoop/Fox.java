import info.gridworld.actor.Critter;
import info.gridworld.actor.Actor;
import info.gridworld.grid.Location;

import java.util.ArrayList;

public class Fox extends Critter {
	
	private int steps; // keeps track of both hunger and nap time
	private boolean napping;
	
	public Fox() {
		setColor(null);
		napping = false;
		steps = 0;
	}
	
	/** 
	 * Gets all the Chickens next to this Fox that this Fox wants to eat.
	 * @return An ArrayList of all adjacent Chickens the Fox is capable 
	 * of eating.
	 */
	public ArrayList<Actor> getActors() {
		ArrayList<Actor> result = null;
		int index = -1;
		
		// after ten turns, the Fox stops napping
		if (napping && steps == 10) {
			napping = false;
			steps = 0;
		}
		
		if (!napping) {
			result = getGrid().getNeighbors(getLocation());
			index = 0;
			while (index < result.size()) {
				if (!(result.get(index) instanceof Chicken)) {
					result.remove(index);
				} else {
					index++;
				}
			}
		} else {
			result = new ArrayList<>();
		}
		
		//System.out.println(result);
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
			Tombstone tomb = new Tombstone();
			tomb.putSelfInGrid(getGrid(), actor.getLocation());
			napping = true;
			steps = 0;
		}
	}
	
	/**
	 * Gets all empty neighboring locations
	 * @return an ArrayList of all neighboring available locations
	 */
	public ArrayList<Location> getMoveLocations() {
		return getGrid().getEmptyAdjacentLocations(getLocation());
	}
	
	/**
	 * Finds the direction to the nearest chicken, and selects a move
	 * location in that direction. 
	 * @param The locations available to be chosen
	 * @return the Location where the Fox will move
	 */
	public Location selectMoveLocation(ArrayList<Location> locs) {
		Location next = null;
		
		//if time, edit code for clarity
		final int LARGE_NUMBER = 10000;
		double distance = LARGE_NUMBER;
		Location target = null;
		int direction = -1;
		
		ArrayList<Location> candidates = new ArrayList<>();
		int choice = -1;
		
		if (napping) {
			next = getLocation();
		} else if (steps >= 20) {
			next = null;
		} else {
			ArrayList<Location> occupied = getGrid().getOccupiedLocations();
			//System.out.println("Actors: " + occupied);
			for (int i = 0; i < occupied.size(); i++) {
				Location actorLoc = occupied.get(i);
				Actor actor = getGrid().get(actorLoc);
				if (actor instanceof Chicken && 
											distanceTo(actorLoc) < distance) {
					distance = distanceTo(actorLoc);
					// System.out.println(actorLoc);
					target = actorLoc;
					candidates = new ArrayList<>();
					candidates.add(target);
				} else if (actor instanceof Chicken && 
											distanceTo(actorLoc) == distance) {
					candidates.add(actorLoc);
				}
			}
			
			//System.out.println("Move candidates: " + candidates);
			
			if (distance == LARGE_NUMBER) {
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
	
	/** 
	 * Moves to loc. If loc is null, then the fox is dead, so it is 
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
				setDirection(getDirection() + random * Location.HALF_RIGHT);
			}
		} else {
			int dir = getLocation().getDirectionToward(loc);
			setDirection(dir);
			//System.out.println("Fox Location before move: " + getLocation());
			moveTo(loc);
		}
		steps++;	
	}
	
	/** 
	 * Calculates the distance from the Fox to another location using 
	 * the distance formula.
	 * @param loc The other location
	 * @return the distance to the other location
	 */
	private double distanceTo(Location loc) {
		//System.out.println("Finding Distance from " + getLocation() + " to " + loc);
		int x1 = getLocation().getCol();
		//System.out.println("My col: " + x1);
		int x2 = loc.getCol();
		//System.out.println("Their col: " + x2);
		int y1 = getLocation().getRow();
		//System.out.println("My row: " + y1);
		int y2 = loc.getRow();
		//System.out.println("Their row: " + y2);
		
		double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
		//System.out.println("Distance from " + getLocation() + " to " + loc
		//	+ ": " + distance);
		return distance;
	}
}
