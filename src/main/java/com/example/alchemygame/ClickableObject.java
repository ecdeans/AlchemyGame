package com.example.alchemygame;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;


// Will make the elements on the left side
public class ClickableObject {
    private double mouseX;
    private double mouseY;
    FXMLLoader loader = new FXMLLoader();
    //loader.setLocation(getClass().getResource("Shapes.fxml"));

    Rectangle newRectangle = new Rectangle(mouseX,mouseY,100,100);
    DraggableObject draggableObject = new DraggableObject();


    public void makeClickable(Rectangle clickableObj, AnchorPane spawnScene){

        //startingPoint = node.localToScene(0,0);

        clickableObj.setOnMousePressed(mouseEvent -> {
            System.out.print("spawn object");
            // Get mouse position
            mouseX = mouseEvent.getSceneX() - 50;
            mouseY = mouseEvent.getSceneY() - 30 - 50;

            // Make the new rectangle
            Rectangle newRectangle = new Rectangle(mouseX,mouseY,100,100);
            newRectangle.setFill(clickableObj.getFill()); // Gets the color of the node clicked on                  !!!!!!!!!!NEEDS TO BE REPLACED WITH IMAGE!!!!!!!!!
            draggableObject.makeDraggable(newRectangle);
            //draggableObject.makeDraggable(newRectangle);

            // Add the new rectangle to the grid
            spawnScene.getChildren().add(newRectangle);

            //System.out.print(mouseEvent.getSceneX() + " " + mouseEvent.getSceneX() + "\n");
        });
    }
}
