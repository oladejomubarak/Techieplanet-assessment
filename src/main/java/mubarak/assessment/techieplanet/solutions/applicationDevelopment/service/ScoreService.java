package mubarak.assessment.techieplanet.solutions.applicationDevelopment.service;

import mubarak.assessment.techieplanet.solutions.applicationDevelopment.model.Score;

import java.util.List;

public interface ScoreService {
    Double calculateMean(List<Double> scores);
    Double calculateMedian(List<Double> scores);
    List<Double> calculateMode(List<Double> scores);
}
