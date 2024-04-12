package com.example.alchemygame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Element {

    private final String elementName;
    private final ImageView elementImageView;

    ClickableObject clickableObj = new ClickableObject();

    // Constructor
    public Element(String elementName, ImageView elementImageView, AnchorPane spawnScene) {
        this.elementName = elementName;
        this.elementImageView = handleElementImage(elementImageView, spawnScene);
    }

    // Getters
    public String getElementName() {
        return this.elementName;
    }

    // Handle element image and make it clickable and draggable
    public ImageView handleElementImage(ImageView imageView, AnchorPane spawnScene) {
        // Use element name to get image path
        String imagePath = "/ART/" + this.elementName.toLowerCase() + ".jpg";

        // Load image using the path
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        // Sets imageView in GUI
        imageView.setImage(image);

        // Makes it clickable and draggable in the main anchor pane
        clickableObj.makeClickable(imageView, spawnScene);

        return imageView;
    }
}
