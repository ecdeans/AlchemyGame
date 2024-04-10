package com.example.alchemygame;

import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

// Test comment

// Check check

public class DraggableObject {
    private double mouseX;
    private double mouseY;

    private double startingX;
    private double startingY;

    //private Point2D startingPoint;

    public void makeDraggable(Node node){

        //startingPoint = node.localToScene(0,0);

        node.setOnMousePressed(mouseEvent -> {
            //System.out.print(startingPoint);
            mouseX = mouseEvent.getSceneX() - node.getTranslateX();
            mouseY = mouseEvent.getSceneY() - node.getTranslateY();
            //System.out.print(mouseEvent.getSceneX() + " " + mouseEvent.getSceneX() + "\n");
        });

        node.setOnMouseDragged(mouseEvent -> {
            //System.out.println(mouseEvent.getSceneX());
            node.setTranslateX(mouseEvent.getSceneX() - mouseX);
            node.setTranslateY(mouseEvent.getSceneY() - mouseY);
            //System.out.print(mouseEvent.getSceneX() + " " + mouseEvent.getSceneX() + "\n");
        });
    }
}
