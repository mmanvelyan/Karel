package org.karel.karel.compiler.condition;

import org.karel.karel.compiler.KarelMap;

public class BeepersPresentCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getBeepersCount() > 0;
    }
}
