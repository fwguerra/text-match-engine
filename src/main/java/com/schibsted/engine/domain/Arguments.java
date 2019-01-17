package com.schibsted.engine.domain;

public class Arguments {
    
    private final String pathToLoad;
    private final String mainClass;
    
    public Arguments(String mainClass, String pathToLoad) {
        this.pathToLoad = pathToLoad;
        this.mainClass = mainClass;
    }

    public String getPathToLoad() {
        return pathToLoad;
    }

    public String getMainClass() {
        return mainClass;
    }
}
