package com.example.alchemygame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class AlchemyGameApplication extends Application{
    @Override
    public void start(Stage stage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("alchemygame-view.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Alchemy Game");
        stage.setResizable(false);
        stage.getIcons().add( new Image(getClass().getResourceAsStream("/ART/PNG64x64/Fire.png")) );
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}