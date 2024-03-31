// this should take one and a half periods

import info.gridworld.actor.Actor;

public class Tombstone extends Actor {
	private int lifetime;
	private final int DEFAULT_LIFETIME = 20;
	
	public Tombstone() {
		lifetime = DEFAULT_LIFETIME;
	}
	
	public void act() {
		lifetime--;
		if (lifetime == 0) {
			removeSelfFromGrid();
		}
	}
}
