package com.epam.port.entity.port;

import com.epam.port.entity.ship.Ship;

/**
 * Created by User on 30.07.2017.
 */
public abstract class BasePier {
    public abstract boolean unloading(Ship ship);
    public abstract void work(boolean work);
}
