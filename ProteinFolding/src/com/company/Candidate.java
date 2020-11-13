package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Candidate {

  List<Aminoacid> sequence;

  Aminoacid[][] folding;
  double fitness;

  public Candidate(String pSequence, int pLatticeSize) {
    this.fitness = 0.0;

    this.sequence = new ArrayList<>();
    for (int i = 0; i < pSequence.length(); ++i) {
      this.sequence.add(new Aminoacid(Character.getNumericValue(pSequence.charAt(i)), i));
    }

    this.folding = new Aminoacid[pLatticeSize][pLatticeSize];
    for (int i = 0; i < pLatticeSize; i++) {
      for (int j = 0; j < pLatticeSize; j++) {
        this.folding[i][j] = new Aminoacid(-1, -1);
      }

    }
    printFolding();
  }

  public void foldSequence() {
    Random r = new Random();
    int low = 0;
    int high = 29;
    int a = 1;
    int b = -1;
    ArrayList<Integer> numbers = new ArrayList<Integer>();
    numbers.add(1);
    numbers.add(-1);
    Collections.shuffle(numbers);

    while (this.fitness < 10.0) {
      int x = r.nextInt(high - low) + low;
      int y = r.nextInt(high - low) + low;
      this.folding[x][y] = this.sequence.get(0);
      for (int i = 1; i < sequence.size(); ++i) {
        x += numbers.get(0);
        y += numbers.get(1);
        System.out.println("get1: " + numbers.get(0));
        System.out.println("get2: " + numbers.get(1));
        System.out.println("x: " + x);
        System.out.println("y: " + y);
        if (x <= 49 && y <= 49 && x >= 0 && y >= 0 && this.folding[x][y].getType() == -1) {
          this.folding[x][y] = this.sequence.get(i);
        } else {
          while (x >= 49 || y >= 49 || x < 0 || y < 0 || this.folding[x][y].getType() != -1) {
            Collections.shuffle(numbers);
            x += numbers.get(0);
            y += numbers.get(1);
          }
          this.folding[x][y] = this.sequence.get(i);
        }
      }
    }
  }

  public void calculateFitness() {
    for (int i = 0; i < this.folding[0].length; ++i) {

    }
  }

  public List<Aminoacid> getSequence() {
    return sequence;
  }

  public Aminoacid[][] getFolding() {
    return folding;
  }

  public void printFolding() {
    for (int i = 0; i < this.folding.length; i++) {
      for (int j = 0; j < this.folding[i].length; j++) {
        System.out.print(
            "(" + this.folding[i][j].getType() + "," + this.folding[i][j].getPosition() + ")"
                + "  ");
      }
      System.out.println();
    }
  }

  public double getFitness() {
    return fitness;
  }
}