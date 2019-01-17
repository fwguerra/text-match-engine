package com.schibsted.engine.service.printer;

import java.util.Map;

public interface Printer {
    void printRanking(Map<String, Double> rankingLineByFile);
}
