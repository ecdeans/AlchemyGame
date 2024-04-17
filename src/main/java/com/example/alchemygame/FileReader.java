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
}

