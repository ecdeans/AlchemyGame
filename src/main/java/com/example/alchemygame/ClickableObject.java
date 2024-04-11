package com.example.alchemygame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ClickableObject {
    // Variables
    private double mouseX;
    private double mouseY;
    // Others
    FXMLLoader loader = new FXMLLoader();
    ImageView newImageView = new ImageView();
    DraggableObject draggableObject = new DraggableObject();

    public void makeClickable(ImageView clickableObj, AnchorPane spawnScene) {
        clickableObj.setOnMousePressed(mouseEvent -> {
            System.out.println("spawn object");
            // Get mouse position
            mouseX = mouseEvent.getSceneX() - 50;
            mouseY = mouseEvent.getSceneY() - 30 - 50;

            // Make new movable image
            ImageView newImageView = new ImageView(clickableObj.getImage());
            newImageView.setLayoutX(mouseX);
            newImageView.setLayoutY(mouseY);

            // Make object draggable
            draggableObject.makeDraggable(newImageView);

            // Add new element to grid
            spawnScene.getChildren().add(newImageView);
        });
    }
}
