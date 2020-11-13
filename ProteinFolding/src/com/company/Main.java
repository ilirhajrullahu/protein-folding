package com.company;

public class Main {

    public static void main(String[] args) {
        Candidate candidate1 = new Candidate(Examples.SEQ20,50);
        candidate1.foldSequence();
        candidate1.printFolding();
    }
}
