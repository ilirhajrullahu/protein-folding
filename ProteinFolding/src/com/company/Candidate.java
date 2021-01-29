package com.company;
import com.company.ProteinGraphic;
import com.company.Aminoacid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Candidate {
  List<Aminoacid> sequence;
  Aminoacid[][] folding;
  double fitness;
  int overlappings;
  int hydrophobContacts;
  List <Integer> foldingDirections;
  ProteinGraphic foldingGraphic;

  public Candidate(String pSequence, int pLatticeSize,int pictureNumber) {
    this.overlappings = 0;
    this.hydrophobContacts = 0;
    this.fitness = 0.0;
    this.sequence = new ArrayList<Aminoacid>();
    this.foldingDirections = new ArrayList<Integer>();
    //this.foldingGraphic = new ProteinGraphic(pictureNumber);
    if (pSequence != "") {
      for (int i = 0; i < pSequence.length(); ++i) {
        this.sequence.add(new Aminoacid(Character.getNumericValue(pSequence.charAt(i)), i));
      }
    }

    this.folding = new Aminoacid[pLatticeSize][pLatticeSize];
    for (int i = 0; i < pLatticeSize; i++) {
      for (int j = 0; j < pLatticeSize; j++) {
        this.folding[i][j] = new Aminoacid(-1, -1);
      }
    }
    this.foldSequence();
    this.calculateHydrophobContacts();
    this.calculateFitness();
  }

  public List<Integer> getFoldingDirections() {
    return foldingDirections;
  }

  public void foldSequence() {
    // 1 = ost
    // 2 = west
    // 3 = süd
    // 4 = nord

    int middleOfMatrix = this.folding.length / 2 ;
    this.folding[middleOfMatrix][middleOfMatrix] = this.sequence.get(0);
    //this.getFoldingGraphic().drawFirstAcid(this.sequence.get(0).getType());
    int lastI = middleOfMatrix;
    int lastJ = middleOfMatrix;

    for (int i = 1; i < this.sequence.size();++i){
      Random ran = new Random();
      int x = ran.nextInt(4) + 1;
      this.foldingDirections.add(x);
      switch(x){
        case 1:
          boolean overlap = false;
          if (checkOverlapping(lastI,lastJ+1) == true){
            this.overlappings += 1;
            overlap = true;
          }
          this.folding[lastI][lastJ+1] = this.sequence.get(i);
          //this.foldingGraphic.draw("ost",this.sequence.get(i).getType(), String.valueOf(i),overlap);
          lastI  = lastI;
          lastJ = lastJ + 1;

          break;
        case 2:
          boolean overlap2 = false;
          if (checkOverlapping(lastI,lastJ-1) == true){
            this.overlappings += 1;
            overlap2 = true;
          }
          this.folding[lastI][lastJ-1] = this.sequence.get(i);
          //this.foldingGraphic.draw("west",this.sequence.get(i).getType(),String.valueOf(i),overlap2);
          lastI  = lastI;
          lastJ = lastJ - 1;
          break;
        case 3:
          boolean overlap3 = false;
          if (checkOverlapping(lastI+1,lastJ) == true){
            this.overlappings += 1;
            overlap3 = true;
          }
          this.folding[lastI+1][lastJ] = this.sequence.get(i);
          //this.foldingGraphic.draw("süd",this.sequence.get(i).getType(),String.valueOf(i),overlap3);
          lastI  = lastI+1;
          lastJ = lastJ;
          break;
        case 4:
          boolean overlap4 = false;
          if (checkOverlapping(lastI-1,lastJ) == true){
            this.overlappings += 1;
            overlap4 = true;
          }
          this.folding[lastI-1][lastJ] = this.sequence.get(i);
          //this.foldingGraphic.draw("nord",this.sequence.get(i).getType(),String.valueOf(i),overlap4);
          lastI  = lastI -1;
          lastJ = lastJ;
          break;
        default:
          System.out.println("No random correct number was created");
      }
    }
    //this.foldingGraphic.saveToFile();
    //System.out.println("Finished folding");
  }

  public void calculateHydrophobContacts(){
    this.hydrophobContacts = 0;
    for (int i = 0; i < this.folding.length; ++i) {
      for (int j = 0; j < this.folding[i].length; ++j) {
        if (checkVerticalFoldingNeighbours(i, j) == true
            || checkHorizontalFoldingNeighbours(i, j) == true) {
          this.hydrophobContacts += 1;
        }
      }
    }
  }

  public void calculateFitness() {
    this.fitness = (double)this.hydrophobContacts / (double)(this.overlappings +1);
    this.fitness = this.fitness * 1000;
    this.fitness = Math.round(this.fitness);
    this.fitness = this.fitness / 1000;
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
    /*if (i - 1 >= 0) {
      if (this.folding[i][j].getType() == 1 && this.folding[i][j].getType() == this.folding[i
          - 1][j].getType()
          && this.folding[i][j].getType() != -1
          && checkSequenceNeighbours(this.folding[i][j], this.folding[i - 1][j]) == false) {
        areVerticalNeighbours = true;
      } else {
        areVerticalNeighbours = false;
      }
    }*/
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
    /*if (j - 1 >= 0) {
      if (this.folding[i][j].getType() == 1 && this.folding[i][j].getType() == this.folding[i][j
          - 1].getType()
          && this.folding[i][j].getType() != -1
          && checkSequenceNeighbours(this.folding[i][j], this.folding[i][j - 1]) == false) {
        areHorizontalNeighbours = true;
      } else {
        areHorizontalNeighbours = false;
      }
    }*/
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

  public void printFoldingDirections() {
    for (int i = 0; i < this.foldingDirections.size(); i++) {
      System.out.println("Direction: " + this.foldingDirections.get(i));
    }
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

  public int getHydrophobeContacts() {
    return this.hydrophobContacts;
  }

  public int getOverlappings() {
    return this.overlappings;
  }

  public ProteinGraphic getFoldingGraphic() {
    return this.foldingGraphic;
  }
}