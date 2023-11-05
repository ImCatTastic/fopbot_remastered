package fobot_2.entity;

import fobot_2.Direction;
import fobot_2.World;
import fobot_2.layoutVisuals.RobotVisual;
import fobot_2.cmath.Vec2;

public class Robot extends Entity implements Obstacle, Collector
{
    private boolean isOff = false;
    private boolean logTrace = false;
    private Direction direction = Direction.UP;
    private int numberOfCoins = 0;

    public Robot(int x, int y)
    {
        this(x, y, Direction.UP, 0, RobotVisual.Family.TRIANGLE_BLUE);
    }

    public Robot(int x, int y, RobotVisual.Family robotFamily)
    {
        this(x, y, Direction.UP, 0, robotFamily);
    }

    public Robot(int x, int y, Direction direction, int numberOfCoins)
    {
        this(x, y, direction, numberOfCoins, RobotVisual.Family.TRIANGLE_BLUE);
    }

    public Robot(int x, int y, Direction direction, int numberOfCoins, RobotVisual.Family robotFamily)
    {
        super(x, y, new RobotVisual(robotFamily),999);
        this.numberOfCoins = numberOfCoins;
        this.direction = direction;

        World.getInstance().checkPosition(x, y);
        World.getInstance().checkNumberOfCoins(numberOfCoins);
        World.getInstance().registerEntity(this);
    }

    public void turnLeft()
    {
        if (isOff)
            return;

        direction = Direction.values()[(direction.ordinal() + 3) % 4];

        if (logTrace){}
            //TODO: Log Trace
    }

    protected void turnRight() //Make protected so people who know can extend and enable this feature :D
    {
        if (isOff)
            return;

        direction = Direction.values()[(direction.ordinal() + 1) % 4];

        if (logTrace){}
        //TODO: Log Trace
    }

    public void move()
    {
        if (isOff)
            return;

        //TODO: implement isFrontClear();
        setPosition(new Vec2(x + direction.getDx(), y + direction.getDy()));
    }

    public boolean isFrontClear()
    {
        return World.getInstance().hasObstacleOnCell(position.add(direction.getDelta()));
    }

    public void crash()
    {
        //TODO: Attach logger to print a useful message
        System.exit(0);
    }

    @Override
    public boolean isBlockingPath(Vec2 pos, Direction direction)
    {
        return false;
    }
}
