package mubarak.assessment.techieplanet.solutions.applicationDevelopment.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ScoreServiceImpl implements ScoreService{

    @Override
    public Double calculateMean(List<Double> scores) {
        return scores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    @Override
    public Double calculateMedian(List<Double> scores) {
        int size = scores.size();
        List<Double> sortedScores = scores.stream().sorted().collect(Collectors.toList());
        if (size % 2 == 0) {
            return (sortedScores.get(size / 2 - 1) + sortedScores.get(size / 2)) / 2;
        } else {
            return sortedScores.get(size / 2);
        }
    }
    @Override
    public List<Double> calculateMode(List<Double> scores) {
        Map<Double, Long> frequencyMap = scores.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        long maxFrequency = Collections.max(frequencyMap.values());
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
