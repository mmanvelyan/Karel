package org.karel.karel.condition;

import org.karel.karel.Condition;
import org.karel.karel.karelmap.Direction;
import org.karel.karel.karelmap.KarelMap;

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
