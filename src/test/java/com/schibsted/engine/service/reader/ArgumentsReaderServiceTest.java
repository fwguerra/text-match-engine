package com.schibsted.engine.service.reader;

import com.schibsted.engine.domain.Arguments;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("ArgumentsReaderServiceTest")
class ArgumentsReaderServiceTest {

    ArgumentsReader argumentsReader = new ArgumentsReaderService();

    @Test
    @DisplayName("Test: Read arguments with class and path - Sucess case")
    void read_withClassAndPath_sucessCase() {
        String[] args = {"MatchTextMain", "/test"};

        Arguments arguments = argumentsReader.read(args);

        assertEquals("/test", arguments.getPathToLoad());
        assertEquals("MatchTextMain", arguments.getMainClass());
    }

    @Test
    @DisplayName("Test: Read arguments with args null - Throws IAE")
    void read_withArgsNull_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> argumentsReader.read(null));
    }
    
    @Test
    @DisplayName("Test: Read arguments with one argument - Throws IAE")
    void read_withOneArgument_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    String[] arguments = {"/usr/local"};
                    argumentsReader.read(arguments);
                });
    }

    @Test
    @DisplayName("Test: Read arguments without class - Throws IAE")
    void read_withoutClass_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    String[] arguments = {null, "/usr/local"};
                    argumentsReader.read(arguments);
                });
    }

    @Test
    @DisplayName("Test: Read arguments with empty class - Throws IAE")
    void read_withEmptyClass_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    String[] arguments = {"", "/usr/local"};
                    argumentsReader.read(arguments);
                });
    }

    @Test
    @DisplayName("Test: Read arguments without directory - Throws IAE")
    void read_withoutDirectory_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    String[] arguments = {"MainClass", null};
                    argumentsReader.read(arguments);
                });
    }

    @Test
    @DisplayName("Test: Read arguments with empty directory - Throws IAE")
    void read_withEmptyDirectory_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> {
                    String[] arguments = {"MainClass", ""};
                    argumentsReader.read(arguments);
                });
    }

}
