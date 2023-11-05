package fobot_2.entity;

import fobot_2.Direction;
import fobot_2.cmath.Vec2;
import fobot_2.layoutVisuals.BlockVisual;
import fobot_2.layoutVisuals.EntityVisual;
import fobot_2.visuals.RenderHints;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;

public class Block extends Entity implements Obstacle
{
    protected Block(int x, int y)
    {
        super(x, y, new BlockVisual(), RenderHints.OverlapMode.HIDE, 998);
    }

    @Override
    public boolean isBlockingPath(Direction direction)
    {
        return false;
    }
}
