package fobot_2;

import fobot_2.entity.Entity;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Chunk
{
    private Map<String, CopyOnWriteArrayList<Entity>> entities = new ConcurrentHashMap<>();
    public Chunk(int x, int y, int size)
    {

    }

    public void addEntity(@NotNull Entity entity)
    {
        if(!entities.containsKey(entity.getType()))
        {
            CopyOnWriteArrayList<Entity> list = new CopyOnWriteArrayList<>();
            list.add(entity);
            entities.put(entity.getType(), list);
        }

        else
            entities.get(entity.getType()).add(entity);
    }

    public void removeEntity(@NotNull Entity entity)
    {
        entityTypeCheck(entity);
        entities.get(entity.getType()).remove(entity);
    }

    public CopyOnWriteArrayList<Entity> getEntitiesFromType(String type)
    {
        return entities.get(type);
    }

    public List<Entity> getAllEntities()
    {
        return entities.values().stream()
            .flatMap(CopyOnWriteArrayList::stream)
            .toList();
    }

    private void entityTypeCheck(@NotNull Entity entity)
    {
        if(!entities.containsKey(entity.getType()))
            throw new IllegalStateException("Entity type doesnt exist in class!");
    }
}
