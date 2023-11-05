package fobot_2.entity;

import fobot_2.layoutVisuals.CoinVisual;

public class Coin extends Entity
{
    private Coin(int x, int y)
    {
        super(x, y, new CoinVisual(),0);
    }

    @Override
    public boolean isObstacle()
    {
        return false;
    }
}
