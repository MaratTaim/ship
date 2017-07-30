package com.epam.port.entity.ship;

/**
 * Created by User on 27.07.2017.
 * Маленький
 */
public class Sloop implements Ship {
    private long volume;
    private final String NAME = "Sloop";

    public Sloop(long volume) {
        this.volume = volume;
    }

    public synchronized long volume() {
        return volume;
    }

    public String name(){
        return NAME;
    }
}
