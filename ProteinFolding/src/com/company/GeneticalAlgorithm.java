package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GeneticalAlgorithm {

  List<Generation> generations;
  double fitnessOfBestUntilNow;
  int hydrophobContactsOfBestUntilNow;
  int overlapsOfBestUntilNow;
  double mutationRate;
  int crossoverRate;

public GeneticalAlgorithm(double pMutationRate, int pCrossOverRate){
  generations = new ArrayList<Generation>();
  this.fitnessOfBestUntilNow = 0.0;
  this.hydrophobContactsOfBestUntilNow = 0;
  this.overlapsOfBestUntilNow = 0;
  this.mutationRate = pMutationRate;
  this.crossoverRate = pCrossOverRate;
}

  public void writeHeadersToCSV() throws IOException {
    File file = new File("ProteinFolding/src/LogFiles/algorithm-log.csv");
    FileWriter writer = new FileWriter(file);
    List<String> tempArr = new ArrayList<>();
    tempArr.add("generationNumber");
    tempArr.add("averageFitnessOfGeneration");
    tempArr.add("fitnessOfBestCandidateOfGeneration");
    tempArr.add("fitnessOfBestCandidateUntilNow");
    tempArr.add("hydrophobContactsOfBestCandidateUntilNow");
    tempArr.add("OverlappingsOfBestCandidateUntilNow");

    String header = tempArr.stream().collect(Collectors.joining(","));
    writer.write(header);
    writer.write("\n");
    writer.close();
  }

  public void writeGenerationToCSV(Generation generation)throws IOException{
    File file = new File("ProteinFolding/src/LogFiles/algorithm-log.csv");
    FileWriter fw = new FileWriter(file,true); //the true flag will append the new data to the next free line
    List<String> tempArr = new ArrayList<>();
    tempArr.add(String.valueOf(generation.getGenerationNumber()));
    tempArr.add(String.valueOf(generation.getAverageFitness()));
    tempArr.add(String.valueOf(generation.getBestCandidateOfGeneration().getFitness()));
    tempArr.add(String.valueOf(this.fitnessOfBestUntilNow));
    tempArr.add(String.valueOf(this.hydrophobContactsOfBestUntilNow));
    tempArr.add(String.valueOf(this.overlapsOfBestUntilNow));


    String line = tempArr.stream().collect(Collectors.joining(","));
    fw.write(line);
    fw.write("\n");
    fw.close();
  }

  public void findBestCandidateOfAlgorithmUntilNow(){
    int candidatePos = 0;
    for ( int i = 1; i < this.generations.size();++i){
      if (this.generations.get(i).getBestCandidateOfGeneration().getFitness() > this.generations.get(candidatePos).getBestCandidateOfGeneration().getFitness()){
        candidatePos = i;
      }
    }

    if (this.fitnessOfBestUntilNow < this.generations.get(candidatePos).getBestCandidateOfGeneration().getFitness()){
      this.fitnessOfBestUntilNow = this.generations.get(candidatePos).getBestCandidateOfGeneration().getFitness();
    }
    if (this.overlapsOfBestUntilNow < this.generations.get(candidatePos).getBestCandidateOfGeneration().getOverlappings()){
      this.overlapsOfBestUntilNow = this.generations.get(candidatePos).getBestCandidateOfGeneration().getOverlappings();
    }
    if (this.hydrophobContactsOfBestUntilNow < this.generations.get(candidatePos).getBestCandidateOfGeneration().getHydrophobeContacts()){
      this.hydrophobContactsOfBestUntilNow = this.generations.get(candidatePos).getBestCandidateOfGeneration().getHydrophobeContacts();
    }
  }

  public List<Generation> getGenerations() {
    return this.generations;
  }

  public double getFitnessOfBestUntilNow() {
    return this.fitnessOfBestUntilNow;
  }

  public int getHydrophobContactsOfBestUntilNow() {
    return this.hydrophobContactsOfBestUntilNow;
  }

  public int getOverlapsOfBestUntilNow() {
    return this.overlapsOfBestUntilNow;
  }
}
