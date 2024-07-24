package org.karel.karel.condition;

import org.karel.karel.karelmap.KarelMap;
import org.karel.karel.node.Operation;

public class InvalidOperationException extends RuntimeException {

    private final KarelMap map;
    private final Operation operation;

    public InvalidOperationException(KarelMap map, Operation operation){
        this.map = map;
        this.operation = operation;
    }

    @Override
    public String getMessage(){
        return "Invalid operation: " + operation.toString() + " on map\n" + map.toString();
    }

}
