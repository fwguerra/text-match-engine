package com.schibsted.engine.service.printer;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PrinterServiceTest")
class PrinterServiceTest {

    Printer printer = new PrinterService();
    ByteArrayOutputStream outContent;
    
    @BeforeEach
    public void beforeEach() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }
    
    @Test
    @DisplayName("Test: Print Ranking with valid results - Prints ranking")
    void printRanking_withValidResults_printsRanking() {
        Map<String, Double> rankingLineByFile = new TreeMap<>();
        rankingLineByFile.put("A.txt", 100.0);
        rankingLineByFile.put("B.txt", 90.0);
        rankingLineByFile.put("C.txt", 80.0);
        rankingLineByFile.put("D.txt", 70.0);
        rankingLineByFile.put("E.txt", 60.0);
        rankingLineByFile.put("F.txt", 50.0);
        rankingLineByFile.put("G.txt", 40.0);
        rankingLineByFile.put("H.txt", 30.0);
        rankingLineByFile.put("I.txt", 20.0);
        rankingLineByFile.put("J.txt", 10.0);
        rankingLineByFile.put("K.txt", 5.0);
        StringBuilder expectedOutputBuilder = new StringBuilder();
        expectedOutputBuilder.append("A.txt : 100,00%\n");
        expectedOutputBuilder.append("B.txt : 90,00%\n");
        expectedOutputBuilder.append("C.txt : 80,00%\n");
        expectedOutputBuilder.append("D.txt : 70,00%\n");
        expectedOutputBuilder.append("E.txt : 60,00%\n");
        expectedOutputBuilder.append("F.txt : 50,00%\n");
        expectedOutputBuilder.append("G.txt : 40,00%\n");
        expectedOutputBuilder.append("H.txt : 30,00%\n");
        expectedOutputBuilder.append("I.txt : 20,00%\n");
        expectedOutputBuilder.append("J.txt : 10,00%\n");
        String notExpecteOutput = "K.txt : 5,00%\n";
        
        printer.printRanking(rankingLineByFile);

        assertEquals(expectedOutputBuilder.toString(), outContent.toString());
        assertNotEquals(notExpecteOutput, outContent.toString());

    }

    @Test
    @DisplayName("Test: Print Ranking with no matches - Prints No Matches Message")
    void printRanking_withNoMatches_printsNoMatchesMessage() {
        Map<String, Double> rankingLineByFile = new HashMap<>();
        rankingLineByFile.put("test1.txt", 0.0);
        String expectedOutput = "No matches found\n";
        
        printer.printRanking(rankingLineByFile);

        assertEquals(expectedOutput, outContent.toString());

    }
    
    @Test
    @DisplayName("Test: Print Ranking with Map in null - Throws IAE")
    void printRanking_withMapNull_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class,
                () -> printer.printRanking(null));
    }
    
    

}
