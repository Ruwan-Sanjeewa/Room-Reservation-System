package lk.ijse.dep.app.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.dep.app.business.BOFactory;
import lk.ijse.dep.app.business.custom.ManageAvailableRoomsBO;
import lk.ijse.dep.app.business.custom.ManageRoomBO;
import lk.ijse.dep.app.business.custom.ManageRoomTypeBO;
import lk.ijse.dep.app.dto.CustomDTO;
import lk.ijse.dep.app.dto.RoomDTO;
import lk.ijse.dep.app.dto.RoomTypeDTO;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DashBoardFormController {
    @FXML
    private JFXComboBox <RoomTypeDTO>cmb_RoomType;
    @FXML
    private AnchorPane dashboard;
    @FXML
    private   JFXDatePicker txt_CheckIn;
    @FXML
    private   JFXDatePicker txt_CheckOut;

    public static LocalDate checkIn;
    public static LocalDate checkOut;
    private String roomType;
    public static List<CustomDTO> availableRooms;

    private ManageRoomTypeBO manageRoomTypeBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ROOM_TYPE_BO);
    private ManageAvailableRoomsBO manageAvailableRoomsBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_AVAILABLE_ROOMS);


    public void initialize(){
        try {
            List<RoomTypeDTO> roomTypes = manageRoomTypeBO.getRoomTypes();
            for (RoomTypeDTO room : roomTypes ) {
                cmb_RoomType.getItems().add(room);
            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


        cmb_RoomType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RoomTypeDTO>() {
            @Override
            public void changed(ObservableValue<? extends RoomTypeDTO> observable, RoomTypeDTO oldValue, RoomTypeDTO selectedItem) {
                roomType=selectedItem.getRoom_type_name();
            }
        });
    }


    @FXML
    private void btnSearch_OnAction(ActionEvent actionEvent) {
        if(cmb_RoomType.getSelectionModel().isEmpty()){
            errorMsg("Select a Room Type ");
            return;
        }

        if(txt_CheckIn.getValue().toString().trim().isEmpty()){
            errorMsg("Select Check in date");
            return;
        }

        if(txt_CheckOut.getValue().toString().trim().isEmpty()){
            errorMsg("Select Check out date");
            return;
        }


        checkIn = txt_CheckIn.getValue();
         checkOut = txt_CheckOut.getValue();
        try {
         availableRooms = manageAvailableRoomsBO.getAvailableRooms(txt_CheckIn.getValue(), txt_CheckOut.getValue(), roomType);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        try {
            Parent Booking= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/BookingForm.fxml"));
            Scene BookingRoomScene = new Scene(Booking);
            Stage primaryStage= (Stage) dashboard.getScene().getWindow();
            primaryStage.setScene(BookingRoomScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Booking");
            primaryStage.show();


        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }


    @FXML
    private void btnRooms_OnAction(ActionEvent actionEvent) {
        try {
            Parent addRoom= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/RoomsForm.fxml"));
            Scene addRoomScene = new Scene(addRoom);
            Stage primaryStage= (Stage) dashboard.getScene().getWindow();
            primaryStage.setScene(addRoomScene);



            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Add Room");
            primaryStage.show();


        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


}

    @FXML
    private void btnAddMealType_OnAction(ActionEvent actionEvent) {
        try {
            Parent addMealType= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/AddMealTypeForm.fxml"));
            Scene addMealTypeScene = new Scene(addMealType);
            Stage primaryStage= (Stage) dashboard.getScene().getWindow();
            primaryStage.setScene(addMealTypeScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Add Meal Type");
            primaryStage.show();


        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    @FXML
    private void btnViewBookings_OnAction(ActionEvent actionEvent) {
        try {
            Parent viewBookings= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/ViewBookingForm.fxml"));
            Scene viewBookingsScene = new Scene(viewBookings);
            Stage primaryStage= (Stage) dashboard.getScene().getWindow();
            primaryStage.setScene(viewBookingsScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("View Bookings");
            primaryStage.show();


        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    @FXML
    private void btnPayment_OnAction(ActionEvent actionEvent) {
        try {
            Parent payment= FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/PaymentForm.fxml"));
            Scene viewBookingsScene = new Scene(payment);
            Stage primaryStage= (Stage) dashboard.getScene().getWindow();
            primaryStage.setScene(viewBookingsScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Payment");
            primaryStage.show();


        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    private void errorMsg(String msg){
        new Alert(Alert.AlertType.ERROR,msg, ButtonType.OK).showAndWait();
    }


}
