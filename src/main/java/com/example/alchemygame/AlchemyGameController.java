package com.example.alchemygame;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.input.MouseEvent;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
//import javafx.scene.control.ScrollBar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.File;
import java.util.Arrays;
import java.util.List;


public class AlchemyGameController {
    final ObservableList<Element> elements = FXCollections.observableArrayList();
    final ObservableList<ElementPair> intersections = FXCollections.observableArrayList();

    final ObservableList<ElementData> elementDataList = FXCollections.observableArrayList(); //Stores all the data for elements
    final ObservableList<ElementData> elementUnlockedList = FXCollections.observableArrayList(); //Stores the unlocked elements
    final ObservableList<ElementData> elementLockedList = FXCollections.observableArrayList(); //Stores the unlocked elements



    @FXML
    private ImageView Air;

    @FXML
    private ImageView Anvil;

    @FXML
    private ImageView Ash;

    @FXML
    private ImageView Brick;

    @FXML
    private AnchorPane CombinePane;

    @FXML
    private ImageView Earth;

    @FXML
    private ImageView Fire;

    @FXML
    private ImageView Furnace;

    @FXML
    private ImageView Glass;

    @FXML
    private ImageView House;

    @FXML
    private ImageView Iron;

    @FXML
    private ImageView Magma;

    @FXML
    private Label NextTargetBox;

    @FXML
    private ImageView Ore;

    @FXML
    private ImageView Pickaxe;

    @FXML
    private ImageView Sand;

    @FXML
    private ImageView Steam;

    @FXML
    private ImageView Stone;

    @FXML
    private ImageView Tree;

    @FXML
    private ImageView Volcano;

    @FXML
    private ImageView Water;

    @FXML
    private ImageView Wood;

    @FXML
    private ColumnConstraints mixingPanel;



    @FXML
    private ImageView Trashcan;

    private String targetElement;

    public void initialize() {


        // Set up trash can
        String trashImagePath = "/ART/PNG64x64/" + Trashcan.getId().toLowerCase() + ".png";
        Image trashImage = new Image(getClass().getResourceAsStream(trashImagePath));
        Trashcan.setImage(trashImage);
        Trashcan.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) { if (!mouseEvent.isPrimaryButtonDown()) { Trashcan.getScene().setCursor(Cursor.HAND); }}
        });
        Trashcan.setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) { if (!mouseEvent.isPrimaryButtonDown()) { Trashcan.getScene().setCursor(Cursor.DEFAULT); }}
        });




        ImageView[] allImages = {Fire, Water, Earth, Air, Tree, Stone, Steam, Wood, Sand, Magma, Ore, Iron, Ash, Volcano, Pickaxe, Furnace, Anvil, Glass, Brick, House};

        // Initialize the base elements
        ImageView[] images = {Fire, Water, Earth, Air, Tree, Stone};
        //ImageView[] images = {Fire, Water, Earth, Air, Tree, Stone, Steam};
        String imagePath = "";
        Image image;
        for (ImageView bases : images) {
            try {
            imagePath = "/ART/PNG64x64/" + bases.getId().toLowerCase() + ".png";
            image = new Image(getClass().getResourceAsStream(imagePath));
            }
            catch(RuntimeException e1){
                try {
                    imagePath = "/ART/PNG16x16/" + bases.getId().toLowerCase() + ".png";
                    image = new Image(getClass().getResourceAsStream(imagePath));
                }
                catch(RuntimeException e2) {
                    imagePath = "/ART/PNG16x16/Default.png";
                    image = new Image(getClass().getResourceAsStream(imagePath));
                }
            }

            bases.setImage(image);
            enableCopy(bases);
        }
        // End initialization


        // Initialize the Unlockable elements
        ImageView[] images2 = {Steam, Wood, Sand, Magma, Ore, Iron, Ash, Volcano, Pickaxe, Furnace, Anvil, Glass, Brick, House};
        for (ImageView bases : images2) {
            try {
                imagePath = "/ART/PNG64x64/" + bases.getId().toLowerCase() + ".png";
                image = new Image(getClass().getResourceAsStream(imagePath));
            }
            catch(RuntimeException e1){
                try {
                    imagePath = "/ART/PNG16x16/" + bases.getId().toLowerCase() + ".png";
                    image = new Image(getClass().getResourceAsStream(imagePath));
                }
                catch(RuntimeException e2) {
                    imagePath = "/ART/PNG16x16/Default.png";
                    image = new Image(getClass().getResourceAsStream(imagePath));
                }
            }

            bases.setImage(image);
            enableCopy(bases);
            bases.setVisible(false); // Hide until unlocked
        }
        // End initialization


        // Create the list of objects
        List<ElementData> elementList;
        elementList = FileReader.getElements("src/main/resources/ElementList.csv");
        elementDataList.addAll(elementList);
        elementLockedList.addAll(elementList);

        for (int i = 0; i <= 5; i++) {
            elementUnlockedList.addAll(elementDataList.get(i));
            elementLockedList.removeAll(elementDataList.get(i));
            System.out.print(elementLockedList.size());
        }
        for (ElementData element : elementLockedList) {
            System.out.print(element.getElementName());
        }

        // Displays all initialized elements and their combinations
        System.out.print("\nInitialized Elements:\n");
        for (ElementData element : elementDataList){
            System.out.printf("%s\n",element);
            System.out.print("creates:\n");
            System.out.printf("%s\n",element.getElementCombinations());
        }


        randomizeTarget();

    }

    // Picks a new target element and displays it
    private void randomizeTarget(){
        if (!elementLockedList.isEmpty()){
            int randomNum = (int) (Math.random() * elementLockedList.size());
            //randomNum = 9;
            String target = "Next Target: %s".formatted(elementLockedList.get(randomNum).getElementName());

            targetElement = elementLockedList.get(randomNum).getElementName();


            NextTargetBox.setText(target);
        }
        else{
            String target = "All Elements Found!";
            NextTargetBox.setText(target);
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
                if (element.getBoundsInLocal().getMinX() <= 0){ element.setX(0); }
                if (element.getBoundsInLocal().getMinY() <= 0){ element.setY(0); }
                if (element.getBoundsInLocal().getMaxX() >= 600){ element.setX(600-64); }
                if (element.getBoundsInLocal().getMaxY() >= 500){ element.setY(500-64); }
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
                Element newElement = new Element(element.getId());
                newElement.setFitHeight(64);
                newElement.setFitWidth(64);
                // Setting the image
                newElement.setImage(element.getImage());
                newElement.setId(newElement.getElementName());
                newElement.setX(mouseEvent.getX() + dragDelta.x);
                newElement.setX(250);
                newElement.setY(mouseEvent.getY() + dragDelta.y);
                newElement.setY(200);
                newElement.setEffect(new DropShadow());

                // add it to array and scene, and enable dragging element
                CombinePane.getChildren().add(newElement);
                elements.add(newElement);
                //System.out.println(elements);
                enableDrag(newElement);
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
            if (combine(pair, x, y)){
                break;
            }
        }
    }

    private boolean combine(ElementPair pair, double x, double y) {
        String[][] elementArray = FileReader.getArray();

        boolean combines = false; // Whether or not a combination happens

        Element a = pair.getA();
        Element b = pair.getB();

        // Convert element names to lowercase for comparison
        String elementA = a.getElementName().toLowerCase();
        String elementB = b.getElementName().toLowerCase();


        // AHHHHHHH THIS IS WHERE IT BREAKS KINDA SORTA BREKAs
        for (ElementData element : elementDataList) { // Loops through the list of elementData
            // store as tuple?


            if (element.getElementName().equals(elementA)){ // If the element data is the data for element A
                if (element.combinesWith(elementB)){ // If element A reacts with element B
                    combineElements(pair, element.getResult(elementB), x, y); // Returns the resulting element as a string
                    //intersections.clear();
                    combines = true;
                }
            }

            // Potentially dead code
            /*
            // Check if the current row contains both elements a and b
                if (Arrays.asList(element).contains(elementA) && Arrays.asList(element).contains(elementB)) {
                // Get the combination result from the CSV
                    String combinationResult = getCombinationResult(element);

                    if (combinationResult != null) {
                        combineElements(pair, combinationResult, x, y);
                }
            }
             */

        }

        return combines;
    }

    // Method to get the combination result from the CSV array
    private String getCombinationResult(String[] elements) {
        // Combination result is in the last column of the CSV
        return elements[elements.length - 1];
    }

    // Method to combine elements
    private void combineElements(ElementPair pair, String newElementName, double x, double y) {
        ImageView[] allImages = {Fire, Water, Earth, Air, Tree, Stone, Steam, Wood, Sand, Magma, Ore, Iron, Ash, Volcano, Pickaxe, Furnace, Anvil, Glass, Brick, House};


        Element newElement = new Element(newElementName);
        newElement.setFitHeight(64);
        newElement.setFitWidth(64);
        newElement.setEffect(new DropShadow());

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

        // Adds to unlockable elements if not yet
        boolean unlocked = false;
        for (ElementData element : elementUnlockedList) {
            if (element.getElementName().equals(newElement.getElementName())) {
                unlocked = true;
            }
        }
        if (!unlocked) {
            System.out.printf("\nUnlocked %s",newElement.getElementName());

            ElementData newUnlockedElement = new ElementData(newElement.getElementName());
            elementUnlockedList.add(newUnlockedElement);


            for (ElementData elementUnlockCheck : elementLockedList) {
                if (elementUnlockCheck.getElementName().equals(newUnlockedElement.getElementName())){
                    elementLockedList.remove(elementUnlockCheck);
                    break;
                }
            }
            //elementLockedList.remove(newUnlockedElement);
            //Steam.getId();
            for (ImageView elementCheck : allImages){
                // UNLOCKS ELEMENT FOR USE
                if (elementCheck.getId().toLowerCase().equals(newUnlockedElement.getElementName())){
                    System.out.print(elementCheck.getId());
                    elementCheck.setVisible(true);
                }
            }

            if (targetElement.equals(newUnlockedElement.getElementName())){ randomizeTarget(); }

            //System.out.print(Steam.getId());

            System.out.printf("\nUnlocked list: %s",elementUnlockedList);
            System.out.printf("\nLocked list: %s",elementLockedList);
        }







    }

    // Deletes whole board
    @FXML
    void clearBoard(MouseEvent event) {
        CombinePane.getChildren().clear();
        intersections.clear();
        elements.clear();
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
