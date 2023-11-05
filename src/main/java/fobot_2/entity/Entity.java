package fobot_2.entity;

import fobot_2.Direction;
import fobot_2.World;
import fobot_2.layoutVisuals.EntityVisual;
import fobot_2.cmath.Vec2;
import fobot_2.visuals.RenderHints.OverlapMode;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public abstract class Entity
{
    final OverlapMode overlapMode;
    final int zLayer;
    final EntityVisual node;

    protected Direction direction;
    protected Vec2 position;
    protected int x;
    protected int y;

    protected Entity(int x, int y, @NotNull EntityVisual node, @NotNull OverlapMode overlapMode, int zLayer)
    {
        this.x = x;
        this.y = y;
        this.position = new Vec2(x, y);

        this.node = node;
        this.overlapMode = overlapMode;
        this.zLayer = zLayer;

        World.getInstance().registerEntity(this);
    }

    protected Entity(int x, int y, EntityVisual node, OverlapMode overlapMode)
    {
        this(x, y, node, overlapMode, 0);
    }

    protected Entity(int x, int y, EntityVisual node, int zLayer)
    {
        this(x, y, node, OverlapMode.SEPARATE, zLayer);
    }

    protected Entity(int x, int y, EntityVisual node)
    {
        this(x, y, node, OverlapMode.SEPARATE, 0);
    }

    public String getType()
    {
        return this.getClass().getSimpleName();
    }
    public int getZLayer()
    {
        return zLayer;
    }
    public OverlapMode getOverlapMode()
    {
        return overlapMode;
    }
    public EntityVisual getNode()
    {
        return node;
    }

    public Vec2 getPosition()
    {
        return position;
    }
    public int getX()
    {
        return x;
    }
    public int getY()
    {
        return y;
    }

    public Direction getDirection()
    {
        return direction;
    }

    /*
    @Override
    public int hashCode()
    {
        return Objects.hash(getType(), x, y);
    }

    @Override
    public boolean equals(Object obj)
    {
        if(obj instanceof Entity entity)
        {
            return
                this.position.equals(entity.position) &&
                this.getType().equals(entity.getType());
        }

        return false;
    }
    */
}
