package com.company;

import java.io.IOException;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    System.out.println("Enter mutation rate: ");
    Scanner in = new Scanner(System.in);
    int mutation = in.nextInt();
    System.out.print("Enter crossover rate: ");
    Scanner in2 = new Scanner(System.in);
    int crossover = in2.nextInt();

    GeneticalAlgorithm geneticalAlgorithm = new GeneticalAlgorithm(mutation,crossover);
    geneticalAlgorithm.writeHeadersToCSV();
    Generation generation1 = new Generation(1,mutation,crossover);
    for (int i = 0; i < 100; ++i) {
      Candidate candidate = new Candidate(Examples.SEQ20, 50, i);
      generation1.addCandidate(candidate);
    }
    generation1.calculateBestCandidateOfGeneration(); // fitness of generation will be calculated too
    geneticalAlgorithm.getGenerations().add(generation1);
    geneticalAlgorithm.findBestCandidateOfAlgorithmUntilNow();
    geneticalAlgorithm.writeGenerationToCSV(generation1);
    geneticalAlgorithm.getGenerations().get(0).fitnessProportionalSelection();

    for (int x = 1; x < 50; ++x) {
      Generation generation = new Generation(x+1,geneticalAlgorithm.getGenerations().get(x-1).getCandidates(),mutation,crossover);
      geneticalAlgorithm.getGenerations().get(0).mutateCandidates();
      generation.calculateBestCandidateOfGeneration();
      geneticalAlgorithm.getGenerations().add(generation);
      geneticalAlgorithm.findBestCandidateOfAlgorithmUntilNow();
      geneticalAlgorithm.writeGenerationToCSV(generation);
      geneticalAlgorithm.getGenerations().get(x).fitnessProportionalSelection();
      geneticalAlgorithm.getGenerations().get(x).mutateCandidates();
    }
  }
}
