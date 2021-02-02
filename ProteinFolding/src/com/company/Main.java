package com.company;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) throws IOException {
    System.out.println("Enter mutation rate: ");
    Scanner in = new Scanner(System.in).useLocale(Locale.US);
    double mutation = in.nextDouble();
    System.out.print("Enter crossover rate: ");
    Scanner in2 = new Scanner(System.in);
    int crossover = in2.nextInt();
    System.out.println("Tournamt size: ");
    Scanner in3 = new Scanner(System.in);
    int tournamentSize = in3.nextInt();

    System.out.println("Stop after seconds: ");
    Scanner in5 = new Scanner(System.in);
    int stopSeconds = in5.nextInt();

    long start = System.currentTimeMillis();
    long end = start + stopSeconds*1000;
    while (System.currentTimeMillis() < end) {
      GeneticalAlgorithm geneticalAlgorithm = new GeneticalAlgorithm(mutation,crossover);
      geneticalAlgorithm.writeHeadersToCSV();
      Generation generation1 = new Generation(1,mutation,crossover);

      for (int i = 0; i < 100; ++i) {
        Candidate candidate = new Candidate(Examples.SEQ36, 100, i);
        //candidate.foldSequence();
        generation1.addCandidate(candidate);
      }

      generation1.calculateBestCandidateOfGeneration(); // fitness of generation will be calculated too
      geneticalAlgorithm.getGenerations().add(generation1);
      geneticalAlgorithm.findBestCandidateOfAlgorithmUntilNow();
      geneticalAlgorithm.writeGenerationToCSV(generation1);

      // fitness proportional selection von x-1 generation sind die start kandidaten für x generation
      for (int x = 1; x < 1200; ++x) {
        //System.out.println("Enter mutation rate: ");
        //Scanner in4 = new Scanner(System.in).useLocale(Locale.US);
        //double mutation4 = in4.nextDouble();
        //Generation generation = new Generation(x+1,geneticalAlgorithm.getGenerations().get(x-1).fitnessProportionalSelection(),mutation,crossover);
        Generation generation = new Generation(x+1,geneticalAlgorithm.getGenerations().get(x-1).tournamentSelection(tournamentSize),mutation,crossover);

        generation.crossOverCandidates();
        generation.mutateCandidates();
        generation.calculateHydrophobContactsOfCandidates();
        generation.calculateFitnessOfCandidates();

        generation.calculateBestCandidateOfGeneration();
        geneticalAlgorithm.getGenerations().add(generation);
        geneticalAlgorithm.findBestCandidateOfAlgorithmUntilNow();
        geneticalAlgorithm.writeGenerationToCSV(generation);
        System.out.println("Generation: " + generation.getGenerationNumber() + " done");
      }
      geneticalAlgorithm.getBestCandidate().getFoldingGraphic().saveBestToFile();
    }
  }
}
