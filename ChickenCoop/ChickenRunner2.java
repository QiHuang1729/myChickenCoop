import info.gridworld.grid.Grid;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.grid.Location;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Rock;
import info.gridworld.actor.Flower;

public class ChickenRunner2
{
    public static void main(String[] args)
    {
        BoundedGrid<Actor> mygrid = new BoundedGrid<Actor>(25,25);
        ActorWorld world = new ActorWorld(mygrid);
        
        world.add(new Location(14,14),new Chicken());
        world.add(new Location(11,13),new Chicken());
        world.add(new Location(16,16),new Chicken());
        world.add(new Location(21,12),new Chicken());
        world.add(new Location(23,24),new Chicken());
        world.add(new Location(20,23),new Chicken());
        world.add(new Location(2,23),new Chicken());
        world.add(new Location(4,21),new Chicken());
        world.add(new Location(5,24),new Chicken());
        
        world.add(new Location(2,2),new Fox());
        
        world.add(new Location(3,0),new Rock());
        world.add(new Location(0,3),new Rock());
        world.add(new Location(15,7),new Rock());
        world.add(new Location(11,6),new Rock());
        world.add(new Location(0,7),new Rock());	
        world.add(new Location(19,1),new Rock());
        		
        world.show();
    }
}
