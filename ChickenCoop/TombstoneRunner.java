import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

public class TombstoneRunner {
	
	public static void main(String[] args) {
		ActorWorld world = new ActorWorld();
		
		world.add(new Location(0, 0), new Tombstone());
		
		world.add(new Location(7, 7), new Tombstone());
		world.show();
	}
}
