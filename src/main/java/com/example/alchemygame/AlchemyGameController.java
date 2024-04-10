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

    DraggableObject draggableObjectPane1 = new DraggableObject();
    DraggableObject draggableObjectPane2 = new DraggableObject();

    public void initialize() {
        //System.out.print("hi");
        draggableObjectPane1.makeDraggable(elementDemo21);
        draggableObjectPane1.makeDraggable(elementDemo2);
        draggableObjectPane1.makeDraggable(elementDemo22);
        draggableObjectPane1.makeDraggable(elementDemo23);
        draggableObjectPane1.makeDraggable(elementDemo24);
        draggableObjectPane1.makeDraggable(elementDemo25);

        draggableObjectPane2.makeDraggable(elementDemo21);
        draggableObjectPane2.makeDraggable(elementDemo2);
        draggableObjectPane2.makeDraggable(elementDemo22);
        draggableObjectPane2.makeDraggable(elementDemo23);
        draggableObjectPane2.makeDraggable(elementDemo24);
        draggableObjectPane2.makeDraggable(elementDemo25);
    }
}
