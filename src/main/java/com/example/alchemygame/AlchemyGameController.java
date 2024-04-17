package com.example.alchemygame;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollBar;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
//import javafx.scene.control.ScrollBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Objects;


public class AlchemyGameController {
    final ObservableList<Element> elements = FXCollections.observableArrayList();
    final ObservableList<ElementPair> intersections = FXCollections.observableArrayList();

    @FXML
    public AnchorPane CombinePane;

    @FXML
    private ImageView Fire;

    @FXML
    private ImageView Water;

    @FXML
    private ImageView Earth;

    @FXML
    private ImageView Air;

    @FXML
    private ImageView Tree;

    @FXML
    private ImageView Stone;

    @FXML
    private ScrollBar elementsScrollBar;

    @FXML
    private ColumnConstraints mixingPanel;

    public void initialize() {
        ImageView[] images = {Fire, Water, Earth, Air, Tree, Stone};
        for (ImageView bases : images) {
            String imagePath = "/ART/" + bases.getId().toLowerCase() + ".jpg";
            Image image = new Image(getClass().getResourceAsStream(imagePath));
            bases.setImage(image);
            enableCopy(bases);
        }
    }

    static class Delta {
        double x, y;
    }

    private void enableDrag(Element element) {
        final Delta dragDelta = new Delta();

        element.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = element.getX() - mouseEvent.getX();
                dragDelta.y = element.getY() - mouseEvent.getY();
                element.getScene().setCursor(Cursor.MOVE);
            }
        });
        element.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                element.getScene().setCursor(Cursor.HAND);
                testIntersections(element, element.getX(), element.getY());
            }
        });
        element.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                element.setX(mouseEvent.getX() + dragDelta.x);
                element.setY(mouseEvent.getY() + dragDelta.y);
            }
        });
        element.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    element.getScene().setCursor(Cursor.HAND);
                }
            }
        });
        element.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    element.getScene().setCursor(Cursor.DEFAULT);
                }
            }
        });
    }


    private void enableCopy(ImageView element) {
        final Delta dragDelta = new Delta();
        element.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // record a delta distance for the drag and drop operation.
                dragDelta.x = element.getX() - mouseEvent.getX();
                dragDelta.y = element.getY() - mouseEvent.getY();

                // Make new movable element
                Element newelement = new Element(element.getId());
                // Setting the image
                newelement.setImage(element.getImage());
                newelement.setId(newelement.getElementName());
                newelement.setX(mouseEvent.getX() + dragDelta.x);
                newelement.setY(mouseEvent.getY() + dragDelta.y);

                // add it to array and scene, and enable dragging element
                CombinePane.getChildren().add(newelement);
                elements.add(newelement);
                //System.out.println(elements);
                enableDrag(newelement);
            }
        });
        element.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                element.getScene().setCursor(Cursor.HAND);
            }
        });
        element.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // May be helpful later when trying to combine clone and drag
            }
        });
        element.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    element.getScene().setCursor(Cursor.HAND);
                }
            }
        });
        element.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    element.getScene().setCursor(Cursor.DEFAULT);
                }
            }
        });
    }

    private void testIntersections(Element src, double x, double y) {
        //String[][] elementArray = FileReader.getArray();
        intersections.clear();

        // for each shape test its intersection with all other shapes.
        for (Element dest : elements) {
            ElementPair pair = new ElementPair(src, dest);
            if (!intersections.contains(pair) && pair.intersects()) {
                intersections.add(pair);
            }
        }
//        for (ElementPair pair : intersections) {
//            for (String[] elements : elementArray) {
//                //System.out.printf("%s%s%s",elements[0], elements[1], elements[2]);
//                // Check if the current row contains both elements a and b
//                String elementA = pair.getA().getElementName().toLowerCase();
//                String elementB = pair.getB().getElementName().toLowerCase();
//                if (elements[0].equals(elementA) & elements[1].equals(elementB) | elements[0].equals(elementB) & elements[1].equals(elementA)) {
//                    // Get the combination result from the CSV
//                    String combinationResult = elements[2];
//                    if (combinationResult != null) {
//                        combineElements(pair, combinationResult, x, y);
//                    }
//                }
//            }
//        }
    }

    //    private void combine(ElementPair pair, double x, double y) {
//        String[][] elementArray = FileReader.getArray();
//
//        Element a = pair.getA();
//        Element b = pair.getB();
//
//        // Convert element names to lowercase for comparison
//        String elementA = a.getElementName().toLowerCase();
//        String elementB = b.getElementName().toLowerCase();
//
//        // AHHHHHHH THIS IS WHERE IT BREAKS KINDA SORTA BREKAs
//        for (String[] elements : elementArray) {
//            //System.out.printf("%s%s%s",elements[0], elements[1], elements[2]);
//            // Check if the current row contains both elements a and b
//                if (elements[0].equals(elementA) && elements[1].equals(elementB) ||
//                        elements[0].equals(elementB) && elements[1].equals(elementA)) {
//                // Get the combination result from the CSV
//                    String combinationResult = elements[2];
//                    combineElements(pair, combinationResult, x, y);
//                }
//            }
//        }

    // Method to combine elements
    private void combineElements(ElementPair pair, String newElementName, double x, double y) {
        Element newElement = new Element(newElementName);

        // Add new element and remove old ones from elements list
        elements.add(newElement);
        elements.remove(pair.getA());
        elements.remove(pair.getB());

        // Remove old elements from pane and add new element
        CombinePane.getChildren().remove(pair.getA());
        CombinePane.getChildren().remove(pair.getB());
        CombinePane.getChildren().add(newElement);

        // Give new element drag
        enableDrag(newElement);

        // Set position for the new element
        newElement.setX(x);
        newElement.setY(y);
    }
}
