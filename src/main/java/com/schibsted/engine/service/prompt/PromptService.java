package com.schibsted.engine.service.prompt;

import com.schibsted.engine.service.printer.Printer;
import com.schibsted.engine.service.printer.PrinterService;
import java.util.Map;
import java.util.Scanner;
import com.schibsted.engine.service.matcher.RankingTextMatch;

public class PromptService implements Prompt {

    private final String PROMPT_MESSAGE = "search> ";
    private final String QUIT_COMMAND = ":quit";
    private final String FINAL_MESSAGE = "Bye!";
    private RankingTextMatch matcherText;
    private Printer printer = new PrinterService();

    public PromptService(RankingTextMatch matcherText) {
        this.matcherText = matcherText;
    }

    @Override
    public void readCommandLine() {
        boolean quit = false;
        Scanner keyboard = new Scanner(System.in);
        while (!quit) {
            System.out.print(PROMPT_MESSAGE);
            final String line = keyboard.nextLine();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equals(QUIT_COMMAND)) {
                quit = true;
                System.out.println(FINAL_MESSAGE);
                continue;
            }
            Map<String, Double> rankingLineByFile = matcherText.getRanking(line);
            printer.printRanking(rankingLineByFile);
        }
    }

}
