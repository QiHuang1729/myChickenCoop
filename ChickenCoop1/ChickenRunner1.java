import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class ChickenRunner1
{
    public static void main(String[] args)
    {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(20,20);
        ActorWorld world = new ActorWorld(mygrid);
		
        world.add(new Location(14,14),new Chicken());
        world.add(new Location(15,12),new Egg());
        
        world.add(new Location(3,0),new Rock());
        world.add(new Location(0,3),new Rock());
        world.add(new Location(15,7),new Rock());
        world.add(new Location(11,6),new Rock());
        world.add(new Location(0,7),new Rock());	
        world.add(new Location(19,1),new Rock());
        		
        world.show();
    }
}
