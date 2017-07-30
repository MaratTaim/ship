package com.epam.port.entity.ship;

/**
 * Created by User on 27.07.2017.
 * Большой
 */
public class Galleon implements Ship{
    private long volume;
    private final String NAME = "Galleon";

    public Galleon(long volume) {
        this.volume = volume;
    }

    public synchronized long volume() {
        return volume;
    }

    public String name(){
        return NAME;
    }
}
