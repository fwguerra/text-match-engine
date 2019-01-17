package com.schibsted.engine.service.loader;

import com.schibsted.engine.domain.ProcessedDirectory;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("DirectoryLoaderServiceTest")
class DirectoryLoaderServiceTest {
    
    DirectoryLoader directoryLoader = new DirectoryLoaderService();
    private static String TEST_PATH;
    
    @BeforeAll
    public static void setTestPath() {
        File resourcesDirectory = new File("src/test/java/resources/");
        TEST_PATH = resourcesDirectory.getAbsolutePath();
    }
    
    @Test
    @DisplayName("Test: Load directory with valid directory - Sucess case")
    void loadDirectory_withValidDirectory_sucessCase() throws IOException {
        ProcessedDirectory processedDirectory = directoryLoader.loadDirectory(TEST_PATH);
        
        assertEquals(4, processedDirectory.getFileNames().size());
        assertTrue(processedDirectory.getFileNames().contains("test1.txt"));
        assertTrue(processedDirectory.getFileNames().contains("test2.txt"));
        assertTrue(processedDirectory.getFileNames().contains("test3.txt"));
        assertTrue(processedDirectory.getFileNames().contains("test4.txt"));
        
        assertEquals(3, processedDirectory.getFilesContainingWordByWord().size());
        assertEquals(2, processedDirectory.getFilesContainingWordByWord().get("hola").size());
        assertEquals(2, processedDirectory.getFilesContainingWordByWord().get("chau").size());
        assertEquals(1, processedDirectory.getFilesContainingWordByWord().get("adios").size());
        assertEquals("test4.txt", processedDirectory.getFilesContainingWordByWord().get("adios").iterator().next());
    }
    
    @Test
    @DisplayName("Test: Load directory with file as directory - Throws IAE")
    void loadDirectory_withFileAsDirectory_throwsIllegalArgumentException() throws IOException {
        assertThrows(IllegalArgumentException.class,
                () -> directoryLoader.loadDirectory(TEST_PATH + "/test1.txt"));
    }
    
}
