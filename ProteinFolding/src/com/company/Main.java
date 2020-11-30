package com.company;

public class Main {

    public static void main(String[] args) {
        Generation generation1 = new Generation();

        for (int i  = 0; i < 10;++i){
            Candidate candidate = new Candidate(Examples.SEQ20,50);
            generation1.addCandidate(candidate);
        }
        generation1.calculateBestCandidate();
        generation1.printCandidates();
    }
}
