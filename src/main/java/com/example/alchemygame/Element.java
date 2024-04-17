package com.example.alchemygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Element extends ImageView{

    private final String elementName;

    // Constructor
    public Element(String elementName) {
        this.elementName = elementName.toLowerCase();
        String imagePath = "/ART/" + elementName.toLowerCase() + ".jpg";
        Image image = new Image(getClass().getResourceAsStream(imagePath));
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
}
