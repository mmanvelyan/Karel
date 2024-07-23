package org.karel.karel.compiler.condition;

import org.karel.karel.compiler.KarelMap;

public class RightIsBlockedCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.hasWall(map.getRobotCoordinates().getRight());
    }
}
