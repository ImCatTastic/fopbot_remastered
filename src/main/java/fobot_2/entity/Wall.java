package fobot_2.entity;

import fobot_2.layoutVisuals.EntityVisual;
import fobot_2.layoutVisuals.WallVisuals;
import fobot_2.visuals.RenderHints;
import org.jetbrains.annotations.NotNull;

public class Wall extends Entity
{
    public Wall(int x, int y)
    {
        super(x, y, new WallVisuals(), RenderHints.OverlapMode.SEPARATE, 999);
    }

    @Override
    public boolean isObstacle()
    {
        return true;
    }
}
