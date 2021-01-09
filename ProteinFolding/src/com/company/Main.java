package com.company;

import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {

    GeneticalAlgorithm geneticalAlgorithm = new GeneticalAlgorithm();
    geneticalAlgorithm.writeHeadersToCSV();
    Generation generation1 = new Generation(0);
    for (int i = 0; i < 100; ++i) {
      Candidate candidate = new Candidate(Examples.SEQ20, 50, i);
      generation1.addCandidate(candidate);
    }
    generation1.calculateBestCandidateOfGeneration(); // fitness of generation will be calculated too

    geneticalAlgorithm.getGenerations().add(generation1);
    geneticalAlgorithm.findBestCandidateOfAlgorithmUntilNow();
    geneticalAlgorithm.writeGenerationToCSV(generation1);

    geneticalAlgorithm.getGenerations().get(0).fitnessProportionalSelection();
    geneticalAlgorithm.getGenerations().get(0).mutateCandidates();

    for (int x = 1; x < 20; ++x) {
      Generation generation = new Generation(x,geneticalAlgorithm.getGenerations().get(x-1).getCandidates());
      generation.calculateBestCandidateOfGeneration();
      geneticalAlgorithm.getGenerations().add(generation);
      geneticalAlgorithm.findBestCandidateOfAlgorithmUntilNow();
      geneticalAlgorithm.writeGenerationToCSV(generation);
      geneticalAlgorithm.getGenerations().get(x).fitnessProportionalSelection();
      geneticalAlgorithm.getGenerations().get(x).mutateCandidates();
    }
  }
}
