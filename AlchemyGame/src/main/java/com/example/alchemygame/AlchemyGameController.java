package com.example.alchemygame;

// Can you see this??? NO LOL
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.shape.Rectangle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AlchemyGameController {

    @FXML
    private Rectangle elementDemo2;

    @FXML
    private Rectangle elementDemo21;

    @FXML
    private Rectangle elementDemo22;

    @FXML
    private Rectangle elementDemo23;

    @FXML
    private Rectangle elementDemo24;

    @FXML
    private Rectangle elementDemo25;

    @FXML
    private ScrollBar elementsScrollBar;

    DraggableObject draggableObject = new DraggableObject();

    public void initialize() {
        //System.out.print("hi");
        draggableObject.makeDraggable(elementDemo21);
        draggableObject.makeDraggable(elementDemo2);
        draggableObject.makeDraggable(elementDemo22);
        draggableObject.makeDraggable(elementDemo23);
        draggableObject.makeDraggable(elementDemo24);
        draggableObject.makeDraggable(elementDemo25);
    }
}
