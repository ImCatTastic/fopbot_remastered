package fobot_2.layoutVisuals;

import javafx.scene.canvas.GraphicsContext;

public class RobotVisual extends DynamicEntityVisual
{
    public enum Family
    {
        TRIANGLE_BLUE,
        SQUARE_AQUA,
        SQUARE_BLUE,
        SQUARE_GREEN,
        SQUARE_ORANGE,
        SQUARE_PURPLE,
        SQUARE_RED,
        SQUARE_YELLOW,
        SQUARE_BLACK,
        SQUARE_WHITE,
        CUSTOM;
        public String getIdentifier()
        {
            return name().toLowerCase();
        }
    }


    private final Family robotFamily;

    public RobotVisual(Family robotFamily)
    {
        this.robotFamily = robotFamily;
    }
    public RobotVisual()
    {
        this.robotFamily = Family.TRIANGLE_BLUE;
    }
    @Override
    public void requestAnimation()
    {

    }

    @Override
    void draw(GraphicsContext gc)
    {

    }
}
