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
    this.sequence = new ArrayList<Aminoacid>();
    if (pSequence != "") {
      for (int i = 0; i < pSequence.length(); ++i) {
        this.sequence.add(new Aminoacid(Character.getNumericValue(pSequence.charAt(i)), i));
        System.out.println(Character.getNumericValue(pSequence.charAt(i)));
      }
    }
    System.out.println("Sequence length: " + pSequence.length());

    this.folding = new Aminoacid[pLatticeSize][pLatticeSize];
    for (int i = 0; i < pLatticeSize; i++) {
      for (int j = 0; j < pLatticeSize; j++) {
        this.folding[i][j] = new Aminoacid(-1, -1);
      }
    }
    //printFolding();
  }

  public void foldSequence() {
    this.folding[0][0] = this.sequence.get(0);
    this.folding[1][0] = this.sequence.get(1);
    this.folding[1][1] = this.sequence.get(2);
    this.folding[1][2] = this.sequence.get(3);
    this.folding[2][2] = this.sequence.get(4);
    this.folding[2][1] = this.sequence.get(5);
    this.folding[3][1] = this.sequence.get(6);
    this.folding[3][2] = this.sequence.get(7);
    this.folding[4][2] = this.sequence.get(8);
    this.folding[5][2] = this.sequence.get(9);
    this.folding[5][3] = this.sequence.get(10);
    this.folding[4][3] = this.sequence.get(11);
    this.folding[3][3] = this.sequence.get(12);
    this.folding[3][4] = this.sequence.get(13);
    this.folding[4][4] = this.sequence.get(14);
    this.folding[5][4] = this.sequence.get(15);
    this.folding[5][5] = this.sequence.get(16);
    this.folding[4][5] = this.sequence.get(17);
    this.folding[3][5] = this.sequence.get(18);
    this.folding[2][5] = this.sequence.get(19);
    System.out.println("Finished folding");
  }

  public void calculateFitness() {
    for (int i = 0; i < this.folding.length; ++i) {
      for (int j = 0; j < this.folding[i].length; ++j) {
        if (checkVerticalFoldingNeighbour(i, j) == true
            || checkHorizontalFoldingNeighbours(i, j) == true) {
          this.fitness += 1.0;
        }
      }
    }
  }

  public List<Aminoacid> getSequence() {
    return this.sequence;
  }

  public Aminoacid[][] getFolding() {
    return this.folding;
  }

  public boolean checkVerticalFoldingNeighbour(int i, int j) {
    boolean areVerticalNeighbours = false;
    if (i - 1 >= 0) {
      if (this.folding[i][j].getType() == 1 && this.folding[i][j].getType() == this.folding[i
          - 1][j].getType()
          && this.folding[i][j].getType() != -1
          && checkSequenceNeighbours(this.folding[i][j], this.folding[i - 1][j]) == false) {
        areVerticalNeighbours = true;
      } else {
        areVerticalNeighbours = false;
      }
    }
    if (i + 1 <= this.folding.length - 1) {
      if (this.folding[i][j].getType() == 1 && this.folding[i][j].getType() == this.folding[i
          + 1][j].getType() &&
          this.folding[i][j].getType() != -1
          && checkSequenceNeighbours(this.folding[i][j], this.folding[i + 1][j]) == false) {
        areVerticalNeighbours = true;
      } else {
        areVerticalNeighbours = false;
      }
    }
    return areVerticalNeighbours;
  }

  public boolean checkHorizontalFoldingNeighbours(int i, int j) {
    boolean areHorizontalNeighbours = false;
    if (j - 1 >= 0) {
      if (this.folding[i][j].getType() == 1 && this.folding[i][j].getType() == this.folding[i][j
          - 1].getType()
          && this.folding[i][j].getType() != -1
          && checkSequenceNeighbours(this.folding[i][j], this.folding[i][j - 1]) == false) {
        areHorizontalNeighbours = true;
      } else {
        areHorizontalNeighbours = false;
      }
    }
    if (j + 1 <= this.folding.length - 1) {
      if (this.folding[i][j].getType() == 1 && this.folding[i][j].getType() == this.folding[i][j
          + 1].getType() &&
          this.folding[i][j].getType() != -1
          && checkSequenceNeighbours(this.folding[i][j], this.folding[i][j + 1]) == false) {
        areHorizontalNeighbours = true;
      } else {
        areHorizontalNeighbours = false;
      }
    }
    return areHorizontalNeighbours;
  }

  public boolean checkSequenceNeighbours(Aminoacid a, Aminoacid b) {
    boolean areNeighbours = true;
    if (a.getPosition() + 1 == b.getPosition()) {
      areNeighbours = true;
    } else {
      areNeighbours = false;
    }
    return areNeighbours;
  }

  public void printFolding() {
    for (int i = 0; i < this.folding.length; i++) {
      for (int j = 0; j < this.folding[i].length; j++) {
        System.out.print(
            "(" + this.folding[i][j].getType() + ", " + this.folding[i][j].getPosition() + " "
                + ")");
      }
      System.out.println();
    }
  }

  public double getFitness() {
    return this.fitness;
  }
}