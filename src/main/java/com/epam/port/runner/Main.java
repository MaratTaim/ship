package com.epam.port.runner;

import com.epam.port.entity.port.Port;
import com.epam.port.thread.Dispatcher;
import com.epam.port.thread.Sea;

import java.util.LinkedList;

/**
 * Created by User on 27.07.2017.
 *
 *
 */
public class Main {
    private static int countOfShips = 15;

    public static void main(String[] args) throws InterruptedException {
        //запускает патоки Pier
        Port port = new Port();

        Sea sea = new Sea(countOfShips);
        Dispatcher dispatcher = new Dispatcher(port,sea,countOfShips);
        Thread trSea = new Thread(sea);
        trSea.start();
        Thread.sleep(200);
        dispatcher.start();

    }
}
