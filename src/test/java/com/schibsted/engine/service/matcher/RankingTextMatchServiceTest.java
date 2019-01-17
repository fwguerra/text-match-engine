package com.schibsted.engine.service.matcher;

import com.schibsted.engine.domain.ProcessedDirectory;
import com.schibsted.engine.service.loader.DirectoryLoaderService;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.schibsted.engine.service.loader.DirectoryLoader;

@DisplayName("RankingTextMatchServiceTest")
class RankingTextMatchServiceTest {
    
    private static String TEST_PATH;
    
    @BeforeAll
    public static void setTestPath() {
        File resourcesDirectory = new File("src/test/java/resources/");
        TEST_PATH = resourcesDirectory.getAbsolutePath();
    }

    @Test
    @DisplayName("Test: Get ranking with one word - Sucess case")
    void getRankingFile_withOneWord_sucessCase() {
        DirectoryLoader fileLoader = new DirectoryLoaderService();
        ProcessedDirectory processedDirectory = fileLoader.loadDirectory(TEST_PATH);
        RankingTextMatch matcherText = new RankingTextMatchService(processedDirectory);

        Map<String, Double> result = matcherText.getRanking("hola");
        
        assertEquals(4, result.size());
        assertEquals(new Double(100.0), result.get("test1.txt"));
        assertEquals(new Double(100.0), result.get("test3.txt"));
        assertEquals(new Double(0.0), result.get("test2.txt"));
        assertEquals(new Double(0.0), result.get("test4.txt"));

    }
    
    @Test
    @DisplayName("Test: Get ranking with two words - Sucess case")
    void getRankingFile_withTwoWords_sucessCase() throws IOException {
        DirectoryLoader fileLoader = new DirectoryLoaderService();
        ProcessedDirectory directoryProcessed = fileLoader.loadDirectory(TEST_PATH);
        RankingTextMatch matcherText = new RankingTextMatchService(directoryProcessed);

        Map<String, Double> result = matcherText.getRanking("hola chau");
        
        assertEquals(4, result.size());
        assertEquals(new Double(50.0), result.get("test1.txt"));
        assertEquals(new Double(50.0), result.get("test2.txt"));
        assertEquals(new Double(100.0), result.get("test3.txt"));
        assertEquals(new Double(0.0), result.get("test4.txt"));

    }
}
