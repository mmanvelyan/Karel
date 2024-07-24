package org.karel.karel.condition;

import org.karel.karel.Condition;
import org.karel.karel.karelmap.KarelMap;

public class FrontIsBlockedCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.hasWall(map.getRobotCoordinates());
    }
}
