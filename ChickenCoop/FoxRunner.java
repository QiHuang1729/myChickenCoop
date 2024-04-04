import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class FoxRunner {
	
	public static void main(String[] args) {
		BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(25,25);
        ActorWorld world = new ActorWorld(mygrid);
        
        // fox 1
        world.add(new Location(3, 3), new Fox());
        
        world.add(new Location(1, 2), new Chicken());
        world.add(new Location(2, 4), new Chicken());
		world.add(new Location(4, 0), new Chicken());
		world.add(new Location(7, 3), new Chicken());
		
		// fox 2
		world.add(new Location(3, 10), new Fox());
        
        world.add(new Location(1, 9), new Chicken());
        world.add(new Location(2, 11), new Chicken());
		world.add(new Location(4, 7), new Chicken());
		world.add(new Location(7, 10), new Chicken());
		
		// fox 3
		world.add(new Location(11, 3), new Fox());
        
        world.add(new Location(9, 2), new Chicken());
        world.add(new Location(10, 4), new Chicken());
		world.add(new Location(12, 0), new Chicken());
		world.add(new Location(15, 3), new Chicken());
		
		// fox 4
		world.add(new Location(10, 9), new Fox());
        
        world.add(new Location(8, 8), new Chicken());
        world.add(new Location(9, 10), new Chicken());
		world.add(new Location(11, 6), new Chicken());
		world.add(new Location(14, 9), new Chicken());
		
		// fox 5
		world.add(new Location(10, 15), new Fox());
        
        world.add(new Location(8, 14), new Chicken());
        world.add(new Location(9, 16), new Chicken());
		world.add(new Location(11, 12), new Chicken());
		world.add(new Location(14, 15), new Chicken());
		
        world.show();
	}
}
