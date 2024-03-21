package org.example.condition;

import org.example.KarelMap;

public class NoBeepersInBagCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getBag() == 0;
    }
}
