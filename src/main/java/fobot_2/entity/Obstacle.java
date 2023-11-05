package fobot_2.entity;

public interface Obstacle
{
    /**
     * Every obstacle needs to declare if an entity attempting to move
     * to this or out of this entity can do so.
     * @param entity the entity requesting to move
     * @return whether this entity is blocking the path
     */
    boolean isBlockingPath(Entity entity);
}
