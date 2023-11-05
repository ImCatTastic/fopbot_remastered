package fobot_2;


import fobot_2.cmath.Vec2;
import fobot_2.entity.Collector;
import fobot_2.entity.DynamicEntity;
import fobot_2.entity.Entity;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class World
{
    private static World instance;
    public static World getInstance()
    {
        return instance;
    }

    public static void createWorld(final int width, final int height)
    {
        if(instance == null)
            World.instance = new World(width, height);
    }

    private final ConcurrentHashMap<String, ConcurrentHashMap<Vec2, CopyOnWriteArrayList<Entity>>> staticEntities = new ConcurrentHashMap<>();
    private final Chunk[] dynamicChunks;

    private final Field[][] fields;

    private final int width;
    private final int height;
    public static int globalDelay = 0;
    protected boolean useGui = true;

    private World(final int width, final int height)
    {
        System.setProperty("sun.java2d.dpiaware", "false");
        System.setProperty("sun.java2d.uiScale", "1.0");

        if (width < 1 || height < 1)
            throw new RuntimeException("Invalid world size: " + width + "x" + height);

        this.width = width;
        this.height = height;

        //Create regular array since we know how many fields we will have.
        this.dynamicChunks = new Chunk[1]; //TODO: Create proper quantity
        this.dynamicChunks[0] = new Chunk(0,0,0);

        fields = new Field[width][height];
        //this.chunks = new Chunk[width * height / 4];
    }

    public int getWidth()
    {
        return width;
    }
    public int getHeight()
    {
        return height;
    }


    public void checkPosition(final int x, final int y)
    {
        if (x > width - 1 || x < 0)
            throw new IllegalArgumentException("Invalid x-coordinate: " + x);

        if (y > height - 1 || y < 0)
            throw new IllegalArgumentException("Invalid y-coordinate: " + y);
    }
    public void checkNumberOfCoins(final int numberOfCoins)
    {
        if (numberOfCoins < 0)
            throw new IllegalArgumentException("Number of coins must be greater than -1!");
    }



    public Entity collectEntity(Entity collector)
    {
        if(!(collector instanceof Collector))
            throw new RuntimeException("Only collector is permitted to collect Entities");

        if(Objects.equals(collector.getPosition(), new Vec2(0, 0)))
        {
            return  null;
        }

        return null;
    }



    public void registerEntity(final Entity entity)
    {
        if(entity instanceof DynamicEntity)
            dynamicChunks[0].addEntity(entity);

        else
        {
            if(!staticEntities.containsKey(entity.getType()))
            {
                CopyOnWriteArrayList<Entity> list = new CopyOnWriteArrayList<>();
                list.add(entity);
                ConcurrentHashMap<Vec2, CopyOnWriteArrayList<Entity>> map = new ConcurrentHashMap<>();
                map.put(entity.getPosition(), list);
                staticEntities.put(entity.getType(), map);
            }

            else
                staticEntities.get(entity.getType()).get(entity.getPosition()).add(entity);
        }
    }

    public boolean hasObstacleOnCell(Vec2 pos)
    {
        for (Map<Vec2, CopyOnWriteArrayList<Entity>> entityMap : staticEntities.values())
            if (entityMap.containsKey(pos) && entityMap.get(pos).get(0).isObstacle())
                return true;

        return false;
    }
}
