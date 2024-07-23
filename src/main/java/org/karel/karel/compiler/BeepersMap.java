package org.karel.karel.compiler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BeepersMap that)) return false;
        return Objects.equals(beepers, that.beepers);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(beepers);
    }
}
