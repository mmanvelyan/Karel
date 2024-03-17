package org.example.conditions;

import org.example.Direction;
import org.example.KarelMap;

public class NotFacingEastCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getDirection() != Direction.EAST;
    }
}
