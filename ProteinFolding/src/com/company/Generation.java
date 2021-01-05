package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

  List<Candidate> candidates;
  Candidate bestCandidate;
  int generationNumber;
  double averageFitness;

  public Generation(int pNumber) {
    this.candidates = new ArrayList<Candidate>();
    this.generationNumber = pNumber;
    this.averageFitness = 0.0;
  }

  public void addCandidate(Candidate candidate) {
    this.candidates.add(candidate);
  }

  public void calculateBestCandidate() {
    int candidatePos = 0;
    for (int i = 1; i < this.candidates.size(); ++i) {
      if (this.candidates.get(i).getFitness() > this.candidates.get(candidatePos).getFitness()) {
        candidatePos = i;
      }
    }
    this.bestCandidate = this.candidates.get(candidatePos);
    this.calculateAverageFitness();
  }

  public void fitnessProportionalSelection() {
    List<Double> fortuneWheel = new ArrayList<Double>();
    for (int i = 0; i < this.candidates.size(); ++i) {
      int frequency = (int) (this.candidates.get(i).getFitness() * 100);
      for (int j = 0; j < frequency; ++j) {
        fortuneWheel.add(this.candidates.get(i).getFitness());
      }
    }
    for (int k = 0; k < 100; ++k) {
      Random ran = new Random();
      double randomFitness = fortuneWheel.get(ran.nextInt(fortuneWheel.size()) + 0);
      System.out.println(randomFitness);
    }
  }
  public void mutation() {
    List<String> directionArray = new ArrayList<String>();
    directionArray.add("ost");
    directionArray.add("west");
    directionArray.add("süd");
    directionArray.add("nord");

    //mutationrate = % chance that a candidate can mutate
    double mutationRate = 0.02;

    for (int i = 0; i < this.candidates.size(); ++i) {
      Random rn = new Random();
      double d = rn.nextDouble(); // random value in range 0.0 - 1.0
      if (d <= mutationRate) {
        System.out.println("Entered mutation process");
        Random directionToMutate = new Random();
        int directionArrayIndex = directionToMutate.nextInt(4);
        System.out.println("Changing: " + String.valueOf(directionArrayIndex));
        Random rn2 = new Random();
        int newDirectionFromMutation = rn2.nextInt(4);
        while (newDirectionFromMutation == directionArrayIndex) {
          newDirectionFromMutation = rn2.nextInt(4);
        }

        System.out.println("Changing to: " + String.valueOf(newDirectionFromMutation+1));

        for (int j = 0; j < this.candidates.get(i).getFoldingDirections().size(); ++j) {
          if (directionArrayIndex + 1 == this.candidates.get(i).getFoldingDirections().get(j)) {
            this.candidates.get(i).getFoldingDirections().set(j,newDirectionFromMutation+1);
          }
        }
      }
    }
  }


  public void printCandidates() {
    for (int i = 0; i < this.candidates.size(); ++i) {
      System.out.println(
          "Fitness: " + (this.candidates.get(i).getFitness() + " Contacts:  " + this.candidates
              .get(i).getHydrophobeContacts() + " Overlappings:  " + this.candidates.get(i)
              .getOverlappings()));
    }
    System.out.println(
        "Best Candidate Fitness: " + bestCandidate.getFitness() + " Best Candidate overlapping: "
            + bestCandidate.getOverlappings());
  }

  public void calculateAverageFitness() {
    double temp = 0.0;
    for (int i = 0; i < this.candidates.size(); ++i) {
      temp += this.candidates.get(i).getFitness();
    }
    this.averageFitness = temp / this.candidates.size();
  }

  public Candidate getBestCandidate() {
    return this.bestCandidate;
  }

  public int getGenerationNumber() {
    return this.generationNumber;
  }

  public double getAverageFitness() {
    return this.averageFitness;
  }
}
