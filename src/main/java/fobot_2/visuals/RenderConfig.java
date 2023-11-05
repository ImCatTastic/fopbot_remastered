package fobot_2.visuals;

import fobot_2.colorProfile.ColorProfile;
import org.jetbrains.annotations.NotNull;

public class RenderConfig
{
    protected int marginSize = 25;
    protected int borderThickness = 4;
    protected int fieldSize = 60;
    protected ColorProfile profile;
    public RenderConfig(@NotNull ColorProfile profile)
    {
        this.profile = profile;
    }
    public int getMarginSize()
    {
        return marginSize;
    }
    public int getBorderThickness()
    {
        return borderThickness;
    }
    public int getFieldSize()
    {
        return fieldSize;
    }

    public void setMarginSize(int marginSize)
    {
        this.marginSize = marginSize;
    }

    public void setBorderThickness(int borderThickness)
    {
        this.borderThickness = borderThickness;
    }

    public void setFieldSize(int fieldSize)
    {
        this.fieldSize = fieldSize;
    }
}
