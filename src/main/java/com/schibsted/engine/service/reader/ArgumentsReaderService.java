package com.schibsted.engine.service.reader;

import com.schibsted.engine.domain.Arguments;

public class ArgumentsReaderService implements ArgumentsReader {

    @Override
    public Arguments read(String[] args) {
        if (null == args || args.length < 2){
            throw new IllegalArgumentException("No enought arguments given to execute.");
        }
        String executionClass = args[0];
        String pathToLoad = args[1];
        if (executionClass == null || "".equals(executionClass)){
            throw new IllegalArgumentException("No execution class given to execute.");
        }
        if (pathToLoad == null || "".equals(pathToLoad)){
            throw new IllegalArgumentException("No directory path given to execute.");
        }
        Arguments arguments = new Arguments(executionClass, pathToLoad);
        System.out.println(String.format("Reading on directory %1$s", arguments.getPathToLoad()));
        return arguments;
        
    }
    
}
