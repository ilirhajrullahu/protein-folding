package com.company;

import java.util.ArrayList;
import java.util.List;

public class Generation {
  List<Candidate> candidates;
  Candidate bestCandidate;
  int generationNumber;

  public Generation(int pNumber) {
    this.candidates = new ArrayList<Candidate>();
    this.generationNumber = pNumber;
  }

  public void addCandidate(Candidate candidate){
    this.candidates.add(candidate);
  }

  public void calculateBestCandidate(){
    int candidatePos = 0;
    for ( int i = 0; i<this.candidates.size();++i){
      if (this.candidates.get(i).getOverlappings() < this.candidates.get(candidatePos).getOverlappings()){
          candidatePos = i;
      }else if (this.candidates.get(i).getOverlappings()==this.candidates.get(candidatePos).getOverlappings() && this.candidates.get(i).getFitness() > this.candidates.get(candidatePos).getFitness()){
        candidatePos = i;
      }
    }
    this.bestCandidate = this.candidates.get(candidatePos);
  }

  public void printCandidates(){
    for (int i = 0; i < this.candidates.size();++i){
      System.out.println("Fitness: " + (this.candidates.get(i).getFitness() + " Contacts:  " + this.candidates.get(i).getHydrophobeContacts()+ " Overlappings:  " + this.candidates.get(i).getOverlappings()));
    }
    System.out.println( "Best Candidate Fitness: " + bestCandidate.getFitness() + " Best Candidate overlapping: " + bestCandidate.getOverlappings());
  }
}
