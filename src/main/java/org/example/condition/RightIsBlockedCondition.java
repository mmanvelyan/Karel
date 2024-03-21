package org.example.condition;

import org.example.KarelMap;

public class RightIsBlockedCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.hasWall(map.getRobotCoordinates().getRight());
    }
}
