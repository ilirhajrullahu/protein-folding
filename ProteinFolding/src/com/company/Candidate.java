package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Candidate {

  List<Integer> sequence;

  int[][] folding;
  double fitness;

  public void Candidate(String pSequence, int pLatticeSize) {
    this.fitness = 0.0;

    this.sequence = new ArrayList<>();
    for (int i = 0; i < pSequence.length();++i){
      this.sequence.add(Character.getNumericValue(pSequence.charAt(i)));
    }
    this.folding = new int[pLatticeSize][pLatticeSize];
    for (int i = 0; i < pLatticeSize; ++i) {
      this.folding[i][i] = 0;
    }
  }

  public void generateFolding(){
    Random r = new Random();
    int low = 0;
    int high = 30;
    while (this.fitness < 0.0){
      for (int i = 0; i < sequence.size();++i){
        int x = r.nextInt(high-low) + low;
        int y = r.nextInt(high-low)+ low ;
        this.folding[x][y] = this.sequence.get(i);
      }

    }
  }
  public void calculateFitness() {

  }

  public List<Integer> getSequence() {
    return sequence;
  }

  public int[][] getFolding() {
    return folding;
  }

  public double getFitness() {
    return fitness;
  }

}