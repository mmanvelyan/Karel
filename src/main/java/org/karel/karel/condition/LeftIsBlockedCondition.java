package org.karel.karel.condition;

import org.karel.karel.Condition;
import org.karel.karel.karelmap.KarelMap;

public class LeftIsBlockedCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.hasWall(map.getRobotCoordinates().getLeft());
    }
}
