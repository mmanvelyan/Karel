package org.karel.karel.condition;

import org.karel.karel.Condition;
import org.karel.karel.karelmap.KarelMap;

public class NotCondition implements Condition {

    private final Condition condition;

    public NotCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public boolean check(KarelMap map) {
        return !condition.check(map);
    }
}
