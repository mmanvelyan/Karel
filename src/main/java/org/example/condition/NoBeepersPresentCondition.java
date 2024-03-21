package org.example.condition;

import org.example.KarelMap;

public class NoBeepersPresentCondition implements Condition {
    @Override
    public boolean check(KarelMap map){
        return map.getBeepersCount() == 0;
    }
}
