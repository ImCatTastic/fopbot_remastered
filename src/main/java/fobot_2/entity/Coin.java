package fobot_2.entity;

import fobot_2.layoutVisuals.CoinVisual;
import org.jetbrains.annotations.NotNull;

public class Coin extends Entity implements Collectible
{
    public Coin(int x, int y)
    {
        super(x, y, new CoinVisual(),0);
    }
}
