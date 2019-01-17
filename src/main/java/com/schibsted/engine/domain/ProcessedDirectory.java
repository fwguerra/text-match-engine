package com.schibsted.engine.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ProcessedDirectory {
    private Map<String, Set<String>> filesContainingWordByWord;
    private List<String> fileNames;
    
    public ProcessedDirectory() {
        this.fileNames = new ArrayList();
        this.filesContainingWordByWord = new HashMap<>();
    }

    public Map<String, Set<String>> getFilesContainingWordByWord() {
        return filesContainingWordByWord;
    }

    public void setFilesContainingWord(Map<String, Set<String>> filesContainingWordByWord) {
        this.filesContainingWordByWord = filesContainingWordByWord;
    }

    public List<String> getFileNames() {
        return fileNames;
    }

    public void setFileNames(List<String> fileNames) {
        this.fileNames = fileNames;
    }
    
    
}
