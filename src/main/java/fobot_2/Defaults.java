package fobot_2;

import fobot_2.colorProfile.ColorProfile;
import javafx.scene.paint.Color;

public class Defaults
{
    public static ColorProfile DARK = new ColorProfile(
        new Color(0.1d,0.1d,0.1d,1),
        new Color(0,1,0,1),
        new Color(0.2d,0.2d,0.2d,1),
        new Color(1,1,1,1)
    );
    public static ColorProfile LIGHT = new ColorProfile(
        new Color(0,0,0,1),
        new Color(0,0,0,1),
        new Color(0,0,0,1),
        new Color(0,0,0,1)
    );

    public static ColorProfile DEFAULT_COLOR_PROFILE = DARK;
}
