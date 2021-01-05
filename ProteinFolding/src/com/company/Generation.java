package com.company;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

  List<Candidate> candidates;
  Candidate bestCandidate;
  int generationNumber;
  double averageFitness;

  public Generation(int gNumber) {
    this.candidates = new ArrayList<Candidate>();
    this.generationNumber = gNumber;
    this.averageFitness = 0.0;
  }

  public Generation(int gNumber2, List<Candidate> pCandidates) {
    this.candidates = new ArrayList<Candidate>();
    for (int i = 0; i < pCandidates.size();++i){
      this.candidates.add(pCandidates.get(i));
    }
    this.generationNumber = gNumber2;
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


  // the candidates with best fitness have higher chance that they will live to the next generation
  public void fitnessProportionalSelection() {
    List<Candidate> fortuneWheel = new ArrayList<Candidate>();
    for (int i = 0; i < this.candidates.size(); ++i) {
      int frequency = (int) (this.candidates.get(i).getFitness() * 100);
      for (int j = 0; j < frequency; ++j) {
        fortuneWheel.add(this.candidates.get(i));
      }
    }
    this.candidates.clear();

    for (int k = 0; k < 100; ++k) {
      Random ran = new Random();
      this.candidates.add(fortuneWheel.get(ran.nextInt(fortuneWheel.size()) + 0));
    }
  }
  public void mutateCandidates() {
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
        Random directionToMutate = new Random();
        int directionArrayIndex = directionToMutate.nextInt(4);
        Random rn2 = new Random();
        int newDirectionFromMutation = rn2.nextInt(4);
        while (newDirectionFromMutation == directionArrayIndex) {
          newDirectionFromMutation = rn2.nextInt(4);
        }

        Random rn3 = new Random();
        int toChange = rn3.nextInt(19);

        while (this.candidates.get(i).getFoldingDirections().get(toChange) != directionArrayIndex+1){
          toChange = rn3.nextInt(19);
        }
        this.candidates.get(i).getFoldingDirections().set(toChange,newDirectionFromMutation+1);
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

  public List<Candidate> getCandidates() {
    return candidates;
  }

  public int getGenerationNumber() {
    return this.generationNumber;
  }

  public double getAverageFitness() {
    return this.averageFitness;
  }
}
