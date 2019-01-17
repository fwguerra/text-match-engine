package com.schibsted.engine.service.matcher;

import java.util.Map;

public interface RankingTextMatch {
    Map<String, Double> getRanking(String line);
    
}
