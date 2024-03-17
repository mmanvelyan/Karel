package org.example;

import java.util.HashMap;
import java.util.Map;

public class BeepersMap {
    private final Map<Coordinates, Integer> beepers;

    public BeepersMap(Map<Coordinates, Integer> beepers) {
        this.beepers = beepers;
    }

    public BeepersMap(){
        this(new HashMap<>());
    }

    public int getBeepersCount(int x, int y){
        if (beepers.get(new Coordinates(x, y)) == null){
            return 0;
        } else {
            return beepers.get(new Coordinates(x, y));
        }
    }

    public int getBeepersCount(Coordinates c){
        return getBeepersCount(c.getX(), c.getY());
    }

    public Map<Coordinates, Integer> getBeepers() {
        return new HashMap<>(beepers);
    }
}
