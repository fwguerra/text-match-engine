package com.schibsted.engine.service.printer;

import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.Map;

public class PrinterService implements Printer {

    private final String NO_MATCHES_MESSAGE = "No matches found";
    private final String PERCENTAGE_FORMAT = "#,##0.00'%'";
    private final Integer SIZE_OF_RANKING = 10;
    private final DecimalFormat numberFormat = new DecimalFormat(PERCENTAGE_FORMAT);

    @Override
    public void printRanking(Map<String, Double> percentageByFileName) {
        validateRankingLineByFile(percentageByFileName);
        if (!hasMatches(percentageByFileName)) {
            System.out.println(NO_MATCHES_MESSAGE);
        } else {
            int rankCounter = 0;
            Iterator percentageByFileNameIterator = percentageByFileName.entrySet().iterator();
            while (rankCounter < this.SIZE_OF_RANKING && percentageByFileNameIterator.hasNext()) {
                Map.Entry<String, Double> filePercent = (Map.Entry<String, Double>)percentageByFileNameIterator.next();
                System.out.println(String.format("%s : %s", filePercent.getKey(), numberFormat.format(filePercent.getValue())));
                rankCounter++;
            }
        }
    }

    private void validateRankingLineByFile(Map<String, Double> rankingLineByFile) {
        if (rankingLineByFile == null || rankingLineByFile.isEmpty()) {
            throw new IllegalArgumentException("There was a problem processing ranking!");
        }
    }

    private boolean hasMatches(Map<String, Double> percentageByFileName) {
        return !(percentageByFileName.values().iterator().next() == 0);
    }

}
