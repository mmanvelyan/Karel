package org.example.condition;

import org.example.Direction;
import org.example.KarelMap;

public class NotFacingWestCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getDirection() != Direction.WEST;
    }
}
