package org.example.conditions;

import org.example.Direction;
import org.example.KarelMap;

public class NotFacingSouthCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getDirection() != Direction.SOUTH;
    }
}
