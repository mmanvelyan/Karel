package org.karel.karel.compiler.condition;

import org.karel.karel.compiler.KarelMap;

public interface Condition {
    boolean check(KarelMap map);
}
