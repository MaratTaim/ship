package com.epam.port.thread;

import com.epam.port.entity.port.PierType;
import com.epam.port.entity.port.Port;
import com.epam.port.entity.ship.Ship;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;

import javax.jws.soap.SOAPBinding;

/**
 * Created by User on 27.07.2017.
 * К диспетчеру приходят корабли из моря run()
 * как только пришел корабль он берет порт и пытается ему передать корабль
 */
public class Dispatcher extends Thread {
    private Port port;
    private Ship ship;
    private Sea sea;
    private int i = 0;
    private int count;
    private final String GALLEON = "Galleon";
    private final String BARK = "Bark";
    private final String SLOOP = "Sloop";

    public Dispatcher(Port pool, Sea sea, int count) {
        this.count = count;
        this.sea = sea;
        this.port = pool;
    }

    public void run() {
        while (i++ < count) {
            ship = sea.getShip();
            System.out.println("ДИСПЕТЧЕР: Из моря приплыл новый корабль - "+ship.name());

            if(ship.name().equals(GALLEON)){
                port.getPier(PierType.BIG, ship);
            }
            else if(ship.name().equals(BARK)){

                port.getPier(PierType.MEDIUM, ship);
            }
            else if(ship.name().equals(SLOOP)){

                port.getPier(PierType.SMALL, ship);
            }
            port.returnPier();

        }
        port.endWork();
        System.out.println("ДИСПЕТЧЕР: Работа на сегодня закончена!");
    }

}
