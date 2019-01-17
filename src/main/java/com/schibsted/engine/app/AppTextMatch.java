package com.schibsted.engine.app;

import com.schibsted.engine.domain.Arguments;
import com.schibsted.engine.domain.ProcessedDirectory;
import com.schibsted.engine.service.loader.DirectoryLoader;
import com.schibsted.engine.service.loader.DirectoryLoaderService;
import com.schibsted.engine.service.matcher.RankingTextMatchService;
import com.schibsted.engine.service.prompt.Prompt;
import com.schibsted.engine.service.prompt.PromptService;
import com.schibsted.engine.service.reader.ArgumentsReader;
import com.schibsted.engine.service.reader.ArgumentsReaderService;
import com.schibsted.engine.service.matcher.RankingTextMatch;

public class AppTextMatch {
    private final String[] arguments;
    
    public AppTextMatch(String[] arguments) {
        this.arguments = arguments;
    }

    public void execute() {
        ArgumentsReader argumentsReader = new ArgumentsReaderService();
        Arguments readArguments = argumentsReader.read(this.arguments);

        DirectoryLoader fileLoader = new DirectoryLoaderService();
        ProcessedDirectory processedDirectory = fileLoader.loadDirectory(readArguments.getPathToLoad());
        System.out.println(String.format("%d files read in directory %2$s", processedDirectory.getFileNames().size(), readArguments.getPathToLoad()));

        RankingTextMatch matcherText = new RankingTextMatchService(processedDirectory);
        Prompt prompt = new PromptService(matcherText);
        prompt.readCommandLine();
    }
    
    
    
}
