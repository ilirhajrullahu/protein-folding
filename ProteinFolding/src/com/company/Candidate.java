package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Candidate {

  List<Aminoacid> sequence;
  Aminoacid[][] folding;
  int fitness;
  int overlappings;

  public Candidate(String pSequence, int pLatticeSize) {
    this.overlappings = 0;
    this.fitness = 0;
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
    this.foldSequence();
    this.calculateFitness();
  }

  public void foldSequence() {
    // 1 = ost
    // 2 = west
    // 3 = süd
    // 4 = nord

    int middleOfMatrix = this.folding.length / 2 ;
    this.folding[middleOfMatrix][middleOfMatrix] = this.sequence.get(0);
    int lastI = middleOfMatrix;
    int lastJ = middleOfMatrix;

    for (int i = 1; i < this.sequence.size();++i){
      Random ran = new Random();
      int x = ran.nextInt(4) + 1;
      System.out.println("Random direction: " + x);
      switch(x){
        case 1:
          if (checkOverlapping(lastI,lastJ+1) == true){
            this.overlappings += 1;
          }
          this.folding[lastI][lastJ+1] = this.sequence.get(i);
          lastI  = lastI;
          lastJ = lastJ + 1;
          break;
        case 2:
          if (checkOverlapping(lastI,lastJ-1) == true){
            this.overlappings += 1;
          }
          this.folding[lastI][lastJ-1] = this.sequence.get(i);
          lastI  = lastI;
          lastJ = lastJ - 1;
          break;
        case 3:
          if (checkOverlapping(lastI+1,lastJ) == true){
            this.overlappings += 1;
          }
          this.folding[lastI+1][lastJ] = this.sequence.get(i);
          lastI  = lastI+1;
          lastJ = lastJ;
          break;
        case 4:
          if (checkOverlapping(lastI-1,lastJ) == true){
            this.overlappings += 1;
          }
          this.folding[lastI-1][lastJ] = this.sequence.get(i);
          lastI  = lastI -1;
          lastJ = lastJ;
          break;
        default:
          System.out.println("No random correct number was created");
      }
    }
    System.out.println("Finished folding");
  }

  public void calculateFitness() {
    for (int i = 0; i < this.folding.length; ++i) {
      for (int j = 0; j < this.folding[i].length; ++j) {
        if (checkVerticalFoldingNeighbours(i, j) == true
            || checkHorizontalFoldingNeighbours(i, j) == true) {
          this.fitness += 1.0;
        }
      }
    }
  }

  public boolean checkOverlapping(int i , int j){
    boolean isOverlapping = false;
    if (this.folding[i][j].getType() == 1 || this.folding[i][j].getType() == 0){
      isOverlapping = true;
    }else{
      isOverlapping = false;
    }
    return isOverlapping;
  }

  public List<Aminoacid> getSequence() {
    return this.sequence;
  }

  public Aminoacid[][] getFolding() {
    return this.folding;
  }

  public boolean checkVerticalFoldingNeighbours(int i, int j) {
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

  public int getFitness() {
    return this.fitness;
  }

  public int getOverlappings() {
    return this.overlappings;
  }
}