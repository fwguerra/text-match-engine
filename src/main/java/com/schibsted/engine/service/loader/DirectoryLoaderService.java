package com.schibsted.engine.service.loader;

import com.schibsted.engine.domain.ProcessedDirectory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class DirectoryLoaderService implements DirectoryLoader {

    @Override
    public ProcessedDirectory loadDirectory(String path) {
        validatePath(path);
        ProcessedDirectory processedDirectory = new ProcessedDirectory();

        try {
            Files.newDirectoryStream(Paths.get(path),
                    fileName -> fileName.toString().endsWith(".txt"))
                    .forEach(pathname -> {
                        loadFilesByWord(pathname.toFile(), processedDirectory.getFilesContainingWordByWord());
                        processedDirectory.getFileNames().add(pathname.toFile().getName());
                    });
        } catch (IOException ex) {
            System.out.println("There was a problem to read a file");
        }
        return processedDirectory;
    }

    private void loadFilesByWord(File file, Map<String, Set<String>> filesContainingWordByWord) {
        try {
            Scanner input = new Scanner(file);
            while (input.hasNext()) {
                String word = input.next();
                Set<String> fileList = filesContainingWordByWord.get(word);
                
                if (fileList == null) {
                    fileList = new HashSet<>();
                }
                
                fileList.add(file.getName());
                filesContainingWordByWord.put(word, fileList);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(String.format("File %s can't be opened", file));
        }
    }

    private void validatePath(String path) {
        File directory = new File(path);
        if (!directory.canRead() || !directory.isDirectory()) {
            throw new IllegalArgumentException("Path [" + path + "] is invalid");
        }
    }

}
