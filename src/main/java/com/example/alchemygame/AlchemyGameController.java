package com.example.alchemygame;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;

public class AlchemyGameController {

    @FXML
    private AnchorPane mainSceneAnchorPlane;

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

    ClickableObject clickableObject = new ClickableObject();

    public void initialize() {

            // Creates elements and fetches image
            Image imageEarth = new Image(getClass().getResourceAsStream("/ART/earth.jpg"));
            elementDemo2.setImage(imageEarth);
            // Makes clickable and draggable in the main Anchor Plane
            clickableObject.makeClickable(elementDemo2, mainSceneAnchorPlane);

            Image imageAir = new Image(getClass().getResourceAsStream("/ART/air.jpg"));
            elementDemo22.setImage(imageAir);
            clickableObject.makeClickable(elementDemo22, mainSceneAnchorPlane);

            Image imageWater = new Image(getClass().getResourceAsStream("/ART/water.jpg"));
            elementDemo21.setImage(imageWater);
            clickableObject.makeClickable(elementDemo21, mainSceneAnchorPlane);

            Image imageFire = new Image(getClass().getResourceAsStream("/ART/fire.jpg"));
            elementDemo23.setImage(imageFire);
            clickableObject.makeClickable(elementDemo23, mainSceneAnchorPlane);

            Image imageStone = new Image(getClass().getResourceAsStream("/ART/stone.jpg"));
            elementDemo24.setImage(imageStone);
            clickableObject.makeClickable(elementDemo24, mainSceneAnchorPlane);

            Image imageTree = new Image(getClass().getResourceAsStream("/ART/tree.jpg"));
            elementDemo25.setImage(imageTree);
            clickableObject.makeClickable(elementDemo25, mainSceneAnchorPlane);

    }
}
