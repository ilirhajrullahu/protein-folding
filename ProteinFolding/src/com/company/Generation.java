package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generation {

  List<Candidate> candidates;
  Candidate bestCandidate;
  int generationNumber;
  double averageFitness;
  int mutationRate;
  int crossOverRate;

  public Generation(int gNumber, int pMutationRate, int pCrossOverRate) {
    this.candidates = new ArrayList<>();
    this.generationNumber = gNumber;
    this.averageFitness = 0.0;
    this.mutationRate = pMutationRate;    //mutationrate = % chance that a candidate can mutate
    this.crossOverRate = pCrossOverRate;     //mutationrate = % chance that a candidate can be subject to a crossover
  }

  public Generation(int gNumber2, List<Candidate> pCandidates, int pMutationRate,
      int pCrossOverRate) {
    this.candidates = new ArrayList<>();
    for (int i = 0; i < pCandidates.size(); ++i) {
      this.candidates.add(pCandidates.get(i));
    }
    this.generationNumber = gNumber2;
    this.averageFitness = 0.0;
    this.mutationRate = pMutationRate;
    this.crossOverRate = pCrossOverRate;
  }

  public void addCandidate(Candidate candidate) {
    this.candidates.add(candidate);
  }

  public void calculateBestCandidateOfGeneration() {
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
    List<Candidate> fortuneWheel = new ArrayList<>();
    for (int i = 0; i < this.candidates.size(); ++i) {
      int frequency = (int) (this.candidates.get(i).getFitness() * 100);
      for (int j = 0; j < frequency; ++j) {
        fortuneWheel.add(this.candidates.get(i));
      }
    }

    for (int j = 0; j < this.candidates.size(); ++j) {
      this.candidates.remove(this.candidates.get(j));
    }
    this.candidates.clear();

    for (int k = 0; k < 100; ++k) {
      Random ran = new Random();
      this.candidates.add(fortuneWheel.get(ran.nextInt(fortuneWheel.size()) + 0));
    }
  }

  public void mutateCandidates() {
    List<String> directionArray = new ArrayList<>();
    directionArray.add("ost");
    directionArray.add("west");
    directionArray.add("süd");
    directionArray.add("nord");

    for (int i = 0; i < this.candidates.size(); ++i) {
      Random rn = new Random();
      double d = rn.nextDouble(); // random value in range 0.0 - 1.0
      if (d <= this.mutationRate) {
        Random directionToMutate = new Random();
        int directionArrayIndex = directionToMutate.nextInt(4);
        Random rn2 = new Random();
        int newDirectionFromMutation = rn2.nextInt(4);
        while (newDirectionFromMutation == directionArrayIndex) {
          newDirectionFromMutation = rn2.nextInt(4);
        }

        Random rn3 = new Random();
        int toChange = rn3.nextInt(19);

        while (this.candidates.get(i).getFoldingDirections().get(toChange)
            != directionArrayIndex + 1) {
          toChange = rn3.nextInt(19);
        }
        this.candidates.get(i).getFoldingDirections().set(toChange, newDirectionFromMutation + 1);
      }
    }
  }

  generation 1 fitness fitnesspropportional
                    ->
  generation 2 mutation und crossover
                und fitnessproportional
                    ->
  generation 3 mutation and crossover
                und fitnessproportional
  public void crossOverCandidates() {
    //   int randomNum = rand.nextInt((max - min) + 1) + min;
    int candidatesForCrossover = (int) (this.candidates.size() * (this.crossOverRate / 100.0f));

    for (int i = 0; i < candidatesForCrossover; ++i) {
      Random rn3 = new Random();
      int crossoverIndex = rn3.nextInt(20);
      List<Integer> tempFoldingDirectionsOfCandidateOne = new ArrayList<>();
      List<Integer> tempFoldingDirectionsOfCandidateTwo = new ArrayList<>();

      // from crossoverindex until the end of the folding of candidate 1
      for (int k = crossoverIndex; k < this.candidates.get(i).getFoldingDirections().size(); ++k) {
        tempFoldingDirectionsOfCandidateOne
            .add(this.candidates.get(i).getFoldingDirections().get(k));
      }
      Random rn2 = new Random();
      int otherRandomCandidate = rn2.nextInt(100);
      // from crossoverindex until the end of the folding of candidate 2
      for (int z = crossoverIndex; z < this.candidates.get(i).getFoldingDirections().size(); ++z) {
        tempFoldingDirectionsOfCandidateTwo
            .add(this.candidates.get(otherRandomCandidate).getFoldingDirections().get(z));
      }

      //insert the extracted part of candidate two into candidate 1 (from crossoverIndex point)
      int tmp1 = crossoverIndex;
      for (int l = 0; l < tempFoldingDirectionsOfCandidateTwo.size(); ++l) {
        this.candidates.get(i).getFoldingDirections()
            .set(tmp1, tempFoldingDirectionsOfCandidateTwo.get(l));
        ++tmp1;
      }

      //insert the extracted part of candidate one into candidate two (from crossoverIndex point)
      int tmp2 = crossoverIndex;
      for (int s = 0; s < tempFoldingDirectionsOfCandidateOne.size(); ++s) {
        this.candidates.get(otherRandomCandidate).getFoldingDirections()
            .set(tmp2, tempFoldingDirectionsOfCandidateTwo.get(s));
        ++tmp2;
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

  public Candidate getBestCandidateOfGeneration() {
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
