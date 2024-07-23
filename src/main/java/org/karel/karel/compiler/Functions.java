package org.karel.karel.compiler;

import java.util.HashMap;
import java.util.Map;

public class Functions {
    private final Map<String, Node> functions = new HashMap<>();

    public void addFunction(String name, Node body){
        functions.put(name, body);
    }

    public Node getFunction(String name){
        return functions.get(name);
    }
}
