package com.schibsted.engine.service.matcher;

import com.schibsted.engine.domain.ProcessedDirectory;
import static java.util.Collections.reverseOrder;
import java.util.Map;
import java.util.Set;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class RankingTextMatchService implements RankingTextMatch {

    private final ProcessedDirectory processedDirectory;

    public RankingTextMatchService(ProcessedDirectory processedDirectory) {
        this.processedDirectory = processedDirectory;
    }

    @Override
    public Map<String, Double> getRanking(String line) {
        Map<String, Double> matchPercentByFile = new HashMap<>();
        Map<String, Integer> ocurrencesWordByFileName = new HashMap<>();
        String[] words = line.split(" ");
        
        for (String word : words) {
            processWord(word, ocurrencesWordByFileName);
        }

        calculateAndSetPercentByFile(ocurrencesWordByFileName, matchPercentByFile, words.length);

        return sortRankingByPercent(matchPercentByFile);
    }

    private void processWord(String word, Map<String, Integer> ocurrencesWordByFileName) {
        Set<String> fileNamesContainingWord = processedDirectory.getFilesContainingWordByWord().get(word);

        if (fileNamesContainingWord != null && !fileNamesContainingWord.isEmpty()) {
            fileNamesContainingWord.forEach((fileName) -> {
                setOcurrencesWordForFile(fileName, ocurrencesWordByFileName);
            });

        }
    }

    private void setOcurrencesWordForFile(String fileName, Map<String, Integer> ocurrencesWordByFileName) {
        Integer ocurrencesWord = 1;
        if (ocurrencesWordByFileName.containsKey(fileName)) {
            ocurrencesWord = ocurrencesWordByFileName.get(fileName) + 1;
        }
        ocurrencesWordByFileName.put(fileName, ocurrencesWord);
    }

    private void calculateAndSetPercentByFile(Map<String, Integer> wordOcurrencesByFileName, Map<String, Double> matchPercentByFileName, int quantityOfWordsInLine) {
        Double quantityOfWordsInLineDouble = new Double(quantityOfWordsInLine);
        processedDirectory.getFileNames().forEach((fileName) -> {
            if (wordOcurrencesByFileName.get(fileName) == null) {
                matchPercentByFileName.put(fileName, 0.0);
            } else {
                matchPercentByFileName.put(fileName, wordOcurrencesByFileName.get(fileName) / quantityOfWordsInLineDouble * 100);
            }
        });
    }

    private Map<String, Double> sortRankingByPercent(Map<String, Double> matchPercentByFileName) {
        return matchPercentByFileName.entrySet().stream()
                .sorted(reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (firstValue, secondValue) -> firstValue, LinkedHashMap::new));
    }

}
