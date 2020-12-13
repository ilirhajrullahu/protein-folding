package com.company;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Logging {

  List<Generation> generations;


  public Logging(){

  }

  public void writeToCSV() throws IOException {
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

}
