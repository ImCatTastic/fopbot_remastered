package fobot_2;

import fobot_2.entity.*;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Field
{
    public Color defaultColor;
    public Image image;
    public int x;
    public int y;

    private final Map<String, CopyOnWriteArrayList<Entity>> entities = new ConcurrentHashMap<>();

    //Only permit one collectible per field
    private final ConcurrentLinkedQueue<Collectible> collectibles = new ConcurrentLinkedQueue<>();
    private final CopyOnWriteArrayList<Obstacle> obstacles = new CopyOnWriteArrayList<>();
    private final ConcurrentHashMap<Direction, Wall> walls = new ConcurrentHashMap<>(4);

    public Field(final int x, final int y)
    {
        this.x = x;
        this.y = y;
    }
    public void setWall(Wall wall)
    {
        walls.put(wall.getDirection(), wall);
    }

    public boolean hasCollectible()
    {
        return !collectibles.isEmpty();
    }
    public boolean hasCollectibleOfType(Class<? extends Collectible> type)
    {
        return !collectibles.isEmpty() && collectibles.peek().getClass() == type;
    }
    public Collectible collectEntity()
    {
        if(!collectibles.isEmpty())
            return collectibles.poll();

        throw new IllegalStateException("No Entity to collect!");
    }
    public void addEntity(Entity entity)
    {
        if(entity instanceof Dynamic)
            throw new IllegalStateException("Dynamic entities cannot be added to fields!");

        if (entity instanceof Collectible collectible)
        {
            if(collectibles.isEmpty() || (collectibles.peek().getClass() == collectible.getClass() && collectible instanceof Stackable))
            {
                collectibles.add(collectible);
                addToAllEntities(entity);
            }
        }

        else
        {
            addToAllEntities(entity);
            if (entity instanceof Obstacle obstacle)
                obstacles.add(obstacle);
        }
    }
    private void addToAllEntities(@NotNull Entity entity)
    {
        entities.computeIfAbsent(
            entity.getType(),
            k -> new CopyOnWriteArrayList<>())
            .add(entity);
    }
    public boolean canEnterField(@NotNull Entity entity)
    {
        Direction dir = entity.getDirection();
        if(walls.containsKey(dir) && walls.get(dir).isBlockingPath(entity))
            return false;

        return obstacles.stream()
            .noneMatch(obstacle -> obstacle.isBlockingPath(entity));
    }
}
