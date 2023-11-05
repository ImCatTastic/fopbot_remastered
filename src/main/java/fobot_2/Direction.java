package fobot_2;

import fobot_2.cmath.Vec2;

public enum Direction
{
    UP(new Vec2(0, 1)),
    RIGHT(new Vec2(1, 0)),
    DOWN(new Vec2(0, -1)),
    LEFT(new Vec2(-1, 0));
    final Vec2 delta;
    Direction(final Vec2 delta)
    {
        this.delta = delta;
    }
    public Vec2 getDelta() {
        return delta;
    }

    public int getDx()
    {
        return delta.x;
    }
    public int getDy()
    {
        return delta.y;
    }
    public boolean isHorizontal() {
        return delta.y == 0;
    }
    public boolean isVertical() {
        return delta.x == 0;
    }
}
