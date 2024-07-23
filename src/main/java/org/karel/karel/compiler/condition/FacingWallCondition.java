package org.karel.karel.compiler.condition;

import org.karel.karel.compiler.Direction;
import org.karel.karel.compiler.KarelMap;

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
