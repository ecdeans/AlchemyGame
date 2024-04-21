package com.example.alchemygame;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public static String[][] getArray() {
        String filePath = "src/main/resources/ElementCombinations.csv";
        return readCSV(filePath);
    }


    // store in format [[(tuple combining elements), result], ...]
    public static String[][] readCSV(String filePath) {
        List<String[]> rows = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                rows.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows.toArray(new String[0][]);
    }



    // Returns a list of ElementData objects
    public static List<ElementData> getElements(String filePath) {
        List<ElementData> elementList = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {

            String line;
            while ((line = reader.readLine()) != null) {
                ElementData element = new ElementData(line.strip());
                List<String[]> combinations = new ArrayList<>(); // Stores combination data

                // Calls for the combination data to be added from other file
                combinations = getCombinations(element.getElementName());
                for (String[] combination : combinations) {
                    element.addCombination(combination[0],combination[1]); // Adds combination to data
                }
                //
                elementList.add(element);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return elementList;
    }

    public static List<String[]> getCombinations(String baseElement){
        List<String[]> combinations = new ArrayList<>(); // Combinations are in format [second element, resulting element]

        // Grabs combinations from the combinations file
        try (BufferedReader reader = new BufferedReader(new java.io.FileReader("src/main/resources/ElementCombinations.csv"))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] lines = line.split(",");

                // If base element is both reagents
                if (lines[0].equals(baseElement) && lines[1].equals(baseElement) ){
                    String[] newCombo = {lines[1],lines[2]};
                    combinations.add(newCombo);
                    //System.out.printf("Added combo to %s: %s + %s = %s\n", baseElement, baseElement, newCombo[0], newCombo[1]);
                }

                // If first element is the base element
                else if (lines[0].equals(baseElement)){
                    String[] newCombo = {lines[1],lines[2]};
                    combinations.add(newCombo);
                    //System.out.printf("Added combo to %s: %s + %s = %s\n", baseElement, baseElement, newCombo[0], newCombo[1]);
                }

                // If second element is the base element
                else if (lines[1].equals(baseElement)){
                    String[] newCombo = {lines[0],lines[2]};
                    combinations.add(newCombo);
                    //System.out.printf("Added combo to %s: %s + %s = %s\n", baseElement, newCombo[0], baseElement, newCombo[1]);
                }


            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        // ==============================================

        return combinations;
    }
}


