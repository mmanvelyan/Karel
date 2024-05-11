package org.example.condition;

import org.example.Direction;
import org.example.KarelMap;

import java.util.Objects;

public class FacingWallCondition implements Condition {

    private final Direction direction;

    public FacingWallCondition(Direction direction) {
        this.direction = Objects.requireNonNull(direction);
    }

    @Override
    public boolean check(KarelMap map) {
        return map.getDirection() == direction;
    }
}
