package org.karel.karel.compiler.condition;

import org.karel.karel.compiler.KarelMap;

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
