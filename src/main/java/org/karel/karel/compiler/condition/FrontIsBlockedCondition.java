package org.karel.karel.compiler.condition;

import org.karel.karel.compiler.KarelMap;

public class FrontIsBlockedCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.hasWall(map.getRobotCoordinates());
    }
}
