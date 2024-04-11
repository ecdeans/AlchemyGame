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
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AlchemyGameController {

    @FXML
    private AnchorPane mainSceneAnchorPlane;

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
    ClickableObject clickableObject = new ClickableObject();

    public void initialize() {
        //System.out.print("hi");
        clickableObject.makeClickable(elementDemo22, mainSceneAnchorPlane);
        clickableObject.makeClickable(elementDemo21, mainSceneAnchorPlane);
        clickableObject.makeClickable(elementDemo2, mainSceneAnchorPlane);
        clickableObject.makeClickable(elementDemo23, mainSceneAnchorPlane);
        clickableObject.makeClickable(elementDemo24, mainSceneAnchorPlane);
        clickableObject.makeClickable(elementDemo25, mainSceneAnchorPlane);
    }
}
