package com.company;

public class Aminoacid {
    int type;
    int position;

  public Aminoacid(int pType, int pPosition){
        this.type = pType;
        this.position = pPosition;
  }

  public int getType() {
    return type;
  }

  public int getPosition() {
    return position;
  }
}
