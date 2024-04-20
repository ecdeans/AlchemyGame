package com.example.alchemygame;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import kotlin.Pair;

public class Element extends ImageView{

    private final String elementName;
    private final ObservableList<Pair<String, String>> combinations = FXCollections.observableArrayList();

    // Constructor
    public Element(String elementName) {
        this.elementName = elementName.toLowerCase();
        //String imagePath = "/ART/PNG/" + elementName.toLowerCase() + ".png";
        //Image image = new Image(getClass().getResourceAsStream(imagePath));

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

        this.setImage(image);
        this.setId(elementName);
    }

    // Getters
    public String getElementName() {
        return this.elementName;
    }

    @Override
    public String toString() {
        return this.elementName;
    }

    // Adds combination data to an element
    public void addCombination(String secondElement, String resultElement){
        combinations.add(-1,new Pair<String, String>(secondElement, resultElement));
    }
}