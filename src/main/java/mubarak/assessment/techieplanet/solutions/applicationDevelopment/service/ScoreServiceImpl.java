package mubarak.assessment.techieplanet.solutions.applicationDevelopment.service;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class ScoreServiceImpl implements ScoreService{

    @Override
    public Double calculateMean(List<Double> scores) {
        return scores.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
    }

    @Override
    public Double calculateMedian(List<Double> scores) {
        List<Double> sortedScores = scores.stream().filter(Objects::nonNull).sorted().toList();
        int size = sortedScores.size();

        if (size == 0) {
            return 0.0;
        }

        if (size % 2 == 0) {
            return (sortedScores.get(size / 2 - 1) + sortedScores.get(size / 2)) / 2;
        } else {
            return sortedScores.get(size / 2);
        }
    }

    @Override
    public List<Double> calculateMode(List<Double> scores) {
        if (scores == null || scores.isEmpty()) {
            return Collections.emptyList();
        }
        Map<Double, Long> frequencyMap = scores.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        if (frequencyMap.isEmpty()) {
            return Collections.emptyList();
        }
        long maxFrequency = Collections.max(frequencyMap.values());
        if (maxFrequency == 1) {
            return Collections.emptyList();
        }
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == maxFrequency)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

}
