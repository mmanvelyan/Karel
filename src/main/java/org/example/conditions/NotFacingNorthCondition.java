package org.example.conditions;

import org.example.Direction;
import org.example.KarelMap;

public class NotFacingNorthCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getDirection() != Direction.NORTH;
    }
}
