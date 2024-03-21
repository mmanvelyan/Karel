package org.example.condition;

import org.example.KarelMap;

public class FrontIsBlockedCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.hasWall(map.getRobotCoordinates());
    }
}
