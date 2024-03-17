package org.example.conditions;

import org.example.KarelMap;

public class LeftIsBlockedCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.hasWall(map.getRobotCoordinates().getLeft());
    }
}
