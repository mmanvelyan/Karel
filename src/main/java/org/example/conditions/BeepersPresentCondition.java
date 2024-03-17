package org.example.conditions;

import org.example.KarelMap;

public class BeepersPresentCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getBeepersCount() > 0;
    }
}
