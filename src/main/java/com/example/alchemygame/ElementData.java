package com.example.alchemygame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kotlin.Pair;
import java.util.ArrayList;

public class ElementData {
    private final String elementName;
    private final Image image;
    private final ArrayList<String[]> combinations = new ArrayList<String[]>();;

    // Constructor
    public ElementData(String elementName) {
        this.elementName = elementName.toLowerCase(); // Sets name data
        //String imagePath = "/ART/PNG/" + elementName.toLowerCase() + ".png";
        //this.image = new Image(getClass().getResourceAsStream(imagePath));
        String imagePath = "";
        Image image;
        try {
            imagePath = "/ART/PNG64x64/" + elementName.toLowerCase() + ".png";
            image = new Image(getClass().getResourceAsStream(imagePath));
        }
        catch(RuntimeException e1){
            try {
                imagePath = "/ART/PNG16x16/" + elementName.toLowerCase() + ".png";
                image = new Image(getClass().getResourceAsStream(imagePath));
            }
            catch(RuntimeException e2) {
                imagePath = "/ART/PNG16x16/Default.png";
                image = new Image(getClass().getResourceAsStream(imagePath));
            }
        }

        this.image = image;

    }

    // Getters
    public String getElementName() {
        return this.elementName;
    }

    public String getElementCombinations(){
        // Return a list of element combinations stored here
        String combinationList = "";
        for (String[] combo : combinations){
            combinationList = combinationList + "\t" + combo[1] + " = "+ getElementName() + " + " + combo[0] + "\n";
        }
        return combinationList;
    }


    @Override
    public String toString() {
        return this.elementName;
    }

    // Adds combination data to an element
    public void addCombination(String secondElement, String resultElement){
        String[] combo = {secondElement, resultElement};
        combinations.add(combo);
    }

    public boolean combinesWith(String secondElement){
        boolean exists = false;
        for (String[] combo : combinations){
            if (combo[0].equals(secondElement)) { exists = true; break; }
        }
        return exists;
    }

    public String getResult(String secondElement){
        String result = "";
        for (String[] combo : combinations){
            if (combo[0].equals(secondElement)) { result = combo[1]; break; }
        }
        return result;
    }

}
