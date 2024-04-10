package com.example.alchemygame;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.shape.Rectangle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AlchemyGameController {

    @FXML
    private Rectangle elementDemo1;

    @FXML
    private Rectangle elementDemo2;

    @FXML
    private ScrollBar elementsScrollBar;

    DraggableObject draggableObject = new DraggableObject();

    public void initialize() {
        //System.out.print("hi");
        draggableObject.makeDraggable(elementDemo1);
        draggableObject.makeDraggable(elementDemo2);
    }
}
