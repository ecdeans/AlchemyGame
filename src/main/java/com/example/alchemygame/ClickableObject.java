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

            // Gets image and sets the ImageView obj
            newImageView.setImage(clickableObj.getImage());
            newImageView.setLayoutX(mouseX);
            newImageView.setLayoutY(mouseY);

            draggableObject.makeDraggable(newImageView);

            // Add new element to grid
            spawnScene.getChildren().add(newImageView);
        });
    }
//    public void makeClickable(Rectangle clickableObj, AnchorPane spawnScene){
//
//        //startingPoint = node.localToScene(0,0);
//
//        clickableObj.setOnMousePressed(mouseEvent -> {
//            System.out.print("spawn object");
//            // Get mouse position
//            mouseX = mouseEvent.getSceneX() - 50;
//            mouseY = mouseEvent.getSceneY() - 30 - 50;
//
//            // Make the new rectangle
//            Rectangle newRectangle = new Rectangle(mouseX,mouseY,100,100);
//            newRectangle.setFill(clickableObj.getFill()); // Gets the color of the node clicked on                  !!!!!!!!!!NEEDS TO BE REPLACED WITH IMAGE!!!!!!!!!
//            draggableObject.makeDraggable(newRectangle);
//            //draggableObject.makeDraggable(newRectangle);
//
//            // Add the new rectangle to the grid
//            spawnScene.getChildren().add(newRectangle);
//
//            //System.out.print(mouseEvent.getSceneX() + " " + mouseEvent.getSceneX() + "\n");
//        });
}
