package org.example.condition;

import org.example.KarelMap;
import org.example.Operation;

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
