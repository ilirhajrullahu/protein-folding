package com.company;

public class Main {

    public static void main(String[] args) {
        Candidate candidate1 = new Candidate(Examples.SEQ20,20);
        candidate1.foldSequence();
        candidate1.calculateFitness();
        candidate1.printFolding();
        System.out.println("Fitness of candidate is: " + candidate1.getFitness());
    }
}
