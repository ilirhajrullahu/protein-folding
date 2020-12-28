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

  public void addCandidate(Candidate candidate){
    this.candidates.add(candidate);
  }

  public void calculateBestCandidate(){
    int candidatePos = 0;
    for ( int i = 1; i<this.candidates.size();++i){
      if (this.candidates.get(i).getFitness() > this.candidates.get(candidatePos).getFitness()){
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
    for (int k = 0; k<100;++k){
      Random ran = new Random();
      double x = fortuneWheel.get(ran.nextInt(fortuneWheel.size()) + 0);
      System.out.println(x);
    }
  }

  public void printCandidates(){
    for (int i = 0; i < this.candidates.size();++i){
      System.out.println("Fitness: " + (this.candidates.get(i).getFitness() + " Contacts:  " + this.candidates.get(i).getHydrophobeContacts()+ " Overlappings:  " + this.candidates.get(i).getOverlappings()));
    }
    System.out.println( "Best Candidate Fitness: " + bestCandidate.getFitness() + " Best Candidate overlapping: " + bestCandidate.getOverlappings());
  }

  public void calculateAverageFitness(){
    double temp = 0.0;
    for (int i = 0; i < this.candidates.size(); ++i){
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
