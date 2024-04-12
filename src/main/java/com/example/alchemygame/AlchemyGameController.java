package com.example.alchemygame;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;

public class AlchemyGameController {

    @FXML
    public AnchorPane mainSceneAnchorPlane;

    @FXML
    private ImageView elementDemo2;

    @FXML
    private ImageView elementDemo21;

    @FXML
    private ImageView elementDemo22;

    @FXML
    private ImageView elementDemo23;

    @FXML
    private ImageView elementDemo24;

    @FXML
    private ImageView elementDemo25;

    @FXML
    private ScrollBar elementsScrollBar;

    @FXML
    // Added this anticipating it might be used to handle collisions
    private ColumnConstraints mixingPanel;



    public void initialize() {

            Element fire = new Element("fire", elementDemo2, mainSceneAnchorPlane);
            Element water = new Element("water", elementDemo21, mainSceneAnchorPlane);
            Element earth = new Element("earth", elementDemo22, mainSceneAnchorPlane);
            Element air = new Element("air", elementDemo23, mainSceneAnchorPlane);
            Element tree = new Element("tree", elementDemo24, mainSceneAnchorPlane);
            Element stone = new Element("stone", elementDemo25, mainSceneAnchorPlane);



    }

}
