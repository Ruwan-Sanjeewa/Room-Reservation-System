package lk.ijse.dep.app.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomsFormController {
    @FXML
    private AnchorPane rooms;

    @FXML
    private void btnViewRoomDetails_OnAction(ActionEvent actionEvent) {
        try {
            Parent roomDetail = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/RoomDetailForm.fxml"));
            Scene roomDetailScene = new Scene(roomDetail);
            Stage primaryStage= (Stage) rooms.getScene().getWindow();
            primaryStage.setScene(roomDetailScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle(" View Room Details ");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }

    @FXML
    private void btnAddRooms_OnAction(ActionEvent actionEvent) {
        try {
            Parent addRoom= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/AddRoomForm.fxml"));
            Scene addRoomScene = new Scene(addRoom);
            Stage primaryStage= (Stage) rooms.getScene().getWindow();
            primaryStage.setScene(addRoomScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Add Rooms");
            primaryStage.show();


        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    @FXML
    private void btnAddRoomTypes_OnAction(ActionEvent actionEvent) {
        try {
            Parent roomType = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/AddRoomTypeForm.fxml"));
            Scene roomTypeScene = new Scene(roomType);
            Stage primaryStage= (Stage) rooms.getScene().getWindow();
            primaryStage.setScene(roomTypeScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Add Room Types");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }



    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        try {
            Parent dashBoard = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/DashBoardForm.fxml"));
            Scene dashBoardScene = new Scene(dashBoard);
            Stage primaryStage= (Stage) rooms.getScene().getWindow();
            primaryStage.setScene(dashBoardScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Room  Reservation  System ");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }
}
