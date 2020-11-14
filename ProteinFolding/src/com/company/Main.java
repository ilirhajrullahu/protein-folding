package com.company;

public class Main {

    public static void main(String[] args) {
        Candidate candidate1 = new Candidate(Examples.SEQ20,20);
        candidate1.foldSequence();
        candidate1.calculateFitness();
        Candidate candidate2 = new Candidate(Examples.SEQ24,20);
        candidate2.foldSequence();
        candidate2.calculateFitness();
        System.out.println("Fitness of candidate is: " + candidate1.getFitness());
        System.out.println("Fitness of candidate is: " + candidate2.getFitness());
    }
}
