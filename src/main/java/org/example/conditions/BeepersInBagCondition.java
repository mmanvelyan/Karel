package org.example.conditions;

import org.example.KarelMap;

public class BeepersInBagCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getBag() > 0;
    }
}
