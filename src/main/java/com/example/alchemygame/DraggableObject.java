package com.example.alchemygame; 

import javafx.geometry.Point2D;
import javafx.scene.Node;

// Test comment


public class DraggableObject {
    private double mouseX;
    private double mouseY;

    private double startingX;
    private double startingY;

    //private Point2D startingPoint;

    public void makeDraggable(Node node){

        //startingPoint = node.localToScene(0,0);

        node.setOnMousePressed(mouseEvent -> {
            System.out.print("hi");
            mouseX = mouseEvent.getX();
            mouseY = mouseEvent.getY();
            //System.out.print(mouseEvent.getSceneX() + " " + mouseEvent.getSceneX() + "\n");
        });

        node.setOnMouseDragged(mouseEvent -> {
            //System.out.println(mouseEvent.getSceneX());
            node.setLayoutX(mouseEvent.getSceneX() - mouseX);
            node.setLayoutY(mouseEvent.getSceneY() - mouseY - 30);
            //System.out.print(mouseEvent.getSceneX() + " " + mouseEvent.getSceneX() + "\n");
        });
    }
}