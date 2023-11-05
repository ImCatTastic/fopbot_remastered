package fobot_2;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Field
{
    public Color defaultColor;
    public Image image;
    public int x;
    public int y;

    public Field(final int x, final int y)
    {
        this.x = x;
        this.y = y;
    }
}
