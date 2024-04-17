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

import java.util.Arrays;


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
        intersections.clear();

        // for each shape test its intersection with all other shapes.
        for (Element dest : elements) {
            ElementPair pair = new ElementPair(src, dest);
            if (!intersections.contains(pair) && pair.intersects()) {
                intersections.add(pair);
            }
        }
        for (ElementPair pair : intersections) {
            combine(pair, x, y);
        }
    }

    private void combine(ElementPair pair, double x, double y) {
        String[][] elementArray = FileReader.getArray();

        Element a = pair.getA();
        Element b = pair.getB();

        // Convert element names to lowercase for comparison
        String elementA = a.getElementName().toLowerCase();
        String elementB = b.getElementName().toLowerCase();

        // AHHHHHHH THIS IS WHERE IT BREAKS KINDA SORTA BREKAs
        for (String[] elements : elementArray) {
            // store as tuple?
            // Check if the current row contains both elements a and b
                if (Arrays.asList(elements).contains(elementA) && Arrays.asList(elements).contains(elementB)) {
                // Get the combination result from the CSV
                    String combinationResult = getCombinationResult(elements);

                    if (combinationResult != null) {
                        combineElements(pair, combinationResult, x, y);
                }
            }
        }
    }

    // Method to get the combination result from the CSV array
    private String getCombinationResult(String[] elements) {
        // Combination result is in the last column of the CSV
        return elements[elements.length - 1];
    }

    // Method to combine elements
    private void combineElements(ElementPair pair, String newElementName, double x, double y) {
        Element newElement = new Element(newElementName);

        // Add new element and remove old ones from elements list
        elements.remove(pair.getA());
        elements.remove(pair.getB());
        elements.add(newElement);

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

//    private void combine(ElementPair pair, double x, double y){
//        if ((Objects.equals(pair.getA().getId().toLowerCase(), "fire") && Objects.equals(pair.getB().getId().toLowerCase(), "water")) ||
//                (Objects.equals(pair.getB().getId().toLowerCase(), "fire") && Objects.equals(pair.getA().getId().toLowerCase(), "water"))){
//
//            Element newelement = new Element("steam");
//
//            elements.add(newelement);
//            elements.remove(pair.getA());
//            elements.remove(pair.getB());
//
//            CombinePane.getChildren().remove(pair.getA());
//            CombinePane.getChildren().remove(pair.getB());
//            CombinePane.getChildren().add(newelement);
//
//            enableDrag(newelement);
//            newelement.setX(x);
//            newelement.setY(y);
//        }
//
//        else if ((Objects.equals(pair.getA().getId().toLowerCase(), "fire") && Objects.equals(pair.getB().getId().toLowerCase(), "earth")) ||
//                (Objects.equals(pair.getB().getId().toLowerCase(), "fire") && Objects.equals(pair.getA().getId().toLowerCase(), "earth"))){
//
//            Element newelement = new Element("volcano");
//
//            elements.add(newelement);
//            elements.remove(pair.getA());
//            elements.remove(pair.getB());
//
//            CombinePane.getChildren().remove(pair.getA());
//            CombinePane.getChildren().remove(pair.getB());
//            CombinePane.getChildren().add(newelement);
//
//            enableDrag(newelement);
//            newelement.setX(x);
//            newelement.setY(y);
//        }
//
//        else if ((Objects.equals(pair.getA().getId().toLowerCase(), "tree") && Objects.equals(pair.getB().getId().toLowerCase(), "stone")) ||
//                (Objects.equals(pair.getB().getId().toLowerCase(), "tree") && Objects.equals(pair.getA().getId().toLowerCase(), "stone"))){
//
//            Element newelement = new Element("pickaxe");
//
//            elements.add(newelement);
//            elements.remove(pair.getA());
//            elements.remove(pair.getB());
//
//            CombinePane.getChildren().remove(pair.getA());
//            CombinePane.getChildren().remove(pair.getB());
//            CombinePane.getChildren().add(newelement);
//
//            enableDrag(newelement);
//            newelement.setX(x);
//            newelement.setY(y);
//        }
//
//        else if (Objects.equals(pair.getA().getId().toLowerCase(), "earth") &&
//                (Objects.equals(pair.getB().getId().toLowerCase(), "earth"))) {
//
//            Element newelement = new Element("stone");
//
//            elements.add(newelement);
//            elements.remove(pair.getA());
//            elements.remove(pair.getB());
//
//            CombinePane.getChildren().remove(pair.getA());
//            CombinePane.getChildren().remove(pair.getB());
//            CombinePane.getChildren().add(newelement);
//
//            enableDrag(newelement);
//            newelement.setX(x);
//            newelement.setY(y);
//        }
//
//        else if ((Objects.equals(pair.getA().getId().toLowerCase(), "earth") && Objects.equals(pair.getB().getId().toLowerCase(), "pickaxe")) ||
//                (Objects.equals(pair.getB().getId().toLowerCase(), "earth") && Objects.equals(pair.getA().getId().toLowerCase(), "pickaxe"))){
//
//            Element newelement = new Element("ore");
//
//            elements.add(newelement);
//            elements.remove(pair.getA());
//            elements.remove(pair.getB());
//
//            CombinePane.getChildren().remove(pair.getA());
//            CombinePane.getChildren().remove(pair.getB());
//            CombinePane.getChildren().add(newelement);
//
//            enableDrag(newelement);
//            newelement.setX(x);
//            newelement.setY(y);
//        }
//
//        else if ((Objects.equals(pair.getA().getId().toLowerCase(), "stone") && Objects.equals(pair.getB().getId().toLowerCase(), "volcano")) ||
//                (Objects.equals(pair.getB().getId().toLowerCase(), "stone") && Objects.equals(pair.getA().getId().toLowerCase(), "volcano"))){
//
//            Element newelement = new Element("furnace");
//
//            elements.add(newelement);
//            elements.remove(pair.getA());
//            elements.remove(pair.getB());
//
//            CombinePane.getChildren().remove(pair.getA());
//            CombinePane.getChildren().remove(pair.getB());
//            CombinePane.getChildren().add(newelement);
//
//            enableDrag(newelement);
//            newelement.setX(x);
//            newelement.setY(y);
//        }
//
//        else if ((Objects.equals(pair.getA().getId().toLowerCase(), "ore") && Objects.equals(pair.getB().getId().toLowerCase(), "furnace")) ||
//                (Objects.equals(pair.getB().getId().toLowerCase(), "ore") && Objects.equals(pair.getA().getId().toLowerCase(), "furnace"))){
//
//            Element newelement = new Element("iron");
//
//            elements.add(newelement);
//            elements.remove(pair.getA());
//            elements.remove(pair.getB());
//
//            CombinePane.getChildren().remove(pair.getA());
//            CombinePane.getChildren().remove(pair.getB());
//            CombinePane.getChildren().add(newelement);
//
//            enableDrag(newelement);
//            newelement.setX(x);
//            newelement.setY(y);
//        }
//    }
//}
