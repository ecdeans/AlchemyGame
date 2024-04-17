package com.example.alchemygame;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader {
    public static String[][] getArray() {
        String filePath = "src/main/resources/ElementCombinations.csv";
//      String fileName = "ElementCombinations.csv";
        String[][] elementArray = readCSV(filePath);

        System.out.println(elementArray);

        for (String[] element : elementArray) {
            for (String word : element) {
                System.out.println(word);
            }
        }

        return readCSV(filePath);

    }


    // store in format [[(tuple combining elements), result], ...]
   public static String[][] readCSV(String filePath) {

        File file = new File(filePath);
        Scanner input = new Scanner(filePath);
        List<String[]> rows = new ArrayList<>();

        while (input.hasNext()) {
            String data = input.next();
            String[] values = data.split(",");
            rows.add(values);
        }

        return rows.toArray(new String[0][]);



//        List<String[]> rows = new ArrayList<>();
//
//        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(filePath))) {
//            String line;
//            while ((line = reader.readLine()) != null) {
//                String[] values = line.split(",");
//                rows.add(values);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return rows.toArray(new String[0][]);
    }
}

