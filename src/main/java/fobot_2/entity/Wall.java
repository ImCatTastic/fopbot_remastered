package fobot_2.entity;

import fobot_2.Direction;
import fobot_2.cmath.Vec2;
import fobot_2.layoutVisuals.EntityVisual;
import fobot_2.layoutVisuals.WallVisuals;
import fobot_2.visuals.RenderHints;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Wall extends Entity implements Obstacle
{
    public Wall(int x, int y, Direction direction)
    {
        super(x, y, new WallVisuals(), RenderHints.OverlapMode.SEPARATE, 999);
        this.direction = direction;
    }

    @Override
    public boolean isBlockingPath(Entity entity)
    {
        return this.direction == entity.direction;
    }
}
