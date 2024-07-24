package org.karel.karel.condition;

import org.karel.karel.Condition;
import org.karel.karel.karelmap.KarelMap;

public class BeepersInBagCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getBag() > 0;
    }
}
