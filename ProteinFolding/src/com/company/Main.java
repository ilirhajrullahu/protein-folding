package com.company;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Generation generation1 = new Generation(100);

        for (int i  = 0; i < 10;++i){
            Candidate candidate = new Candidate(Examples.SEQ20,50);
            generation1.addCandidate(candidate);
        }
        generation1.calculateBestCandidate();
        generation1.printCandidates();
        Logging logging = new Logging();
        logging.writeToCSV();

    }
}
