package com.company;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        com.company.Generation generation1 = new Generation(100);

        for (int i  = 0; i < 100;++i){
            com.company.Candidate candidate = new Candidate(Examples.SEQ20,50,i);
            generation1.addCandidate(candidate);
        }
        generation1.calculateBestCandidate();
        generation1.printCandidates();
        for (int j = 0; j < 1;++j){
            generation1.fitnessProportionalSelection();
            generation1.mutateCandidates();
            generation1.calculateBestCandidate();
        }
        generation1.printCandidates();
    }
}
