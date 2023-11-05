package fobot_2.entity;

import fobot_2.World;
import fobot_2.layoutVisuals.EntityVisual;
import fobot_2.cmath.Vec2;
import fobot_2.visuals.RenderHints;
import org.jetbrains.annotations.NotNull;

public abstract class DynamicEntity extends Entity
{
    public DynamicEntity(int x, int y, @NotNull EntityVisual visuals, @NotNull RenderHints.OverlapMode overlapMode, int zLayer)
    {
        super(x, y, visuals, overlapMode, zLayer);
    }

    public DynamicEntity(int x, int y, @NotNull EntityVisual visuals, @NotNull RenderHints.OverlapMode overlapMode)
    {
        this(x, y, visuals, overlapMode, 0);
    }

    public DynamicEntity(int x, int y, @NotNull EntityVisual visuals, int zLayer)
    {
        this(x, y, visuals, RenderHints.OverlapMode.SEPARATE, zLayer);
    }

    public DynamicEntity(int x, int y, @NotNull EntityVisual visuals)
    {
        super(x, y, visuals, RenderHints.OverlapMode.SEPARATE, 0);
    }

    public void setPosition(@NotNull Vec2 position)
    {
        World.getInstance().checkPosition(position.x, position.y);
        this.pos = position;
    }
    public void setX(int x)
    {
        this.x = x;
    }
    public void setY(int y)
    {
        this.y = y;
    }
}
