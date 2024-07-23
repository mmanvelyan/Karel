package org.karel.karel.compiler.condition;

import org.karel.karel.compiler.KarelMap;

public class BeepersInBagCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getBag() > 0;
    }
}
