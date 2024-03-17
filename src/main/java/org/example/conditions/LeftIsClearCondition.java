package org.example.conditions;

import org.example.KarelMap;

public class LeftIsClearCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return !map.hasWall(map.getRobotCoordinates().getLeft());
    }
}
