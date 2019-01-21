package lk.ijse.dep.app.main;

import javafx.animation.TranslateTransition;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {


        FileHandler fileHandler = new FileHandler("error.log", true);
        fileHandler.setFormatter(new SimpleFormatter());
        Logger.getLogger("").addHandler(fileHandler);


        Parent load = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/DashBoardForm.fxml"));
        Scene scene = new Scene(load);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Room Reservation System");

    }
}
