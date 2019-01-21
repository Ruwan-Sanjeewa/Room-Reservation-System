package lk.ijse.dep.app.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.app.business.BOFactory;
import lk.ijse.dep.app.business.custom.ManageBookingBO;

import lk.ijse.dep.app.dto.CustomDTO;

import lk.ijse.dep.app.view.util.ViewBookingsTM;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ViewBookingFormController {

    @FXML
    private TableView <ViewBookingsTM>tbl_ViewBookings;
    @FXML
    private AnchorPane viewBookings;

    private ManageBookingBO manageBookingBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKING_BO);
    public void initialize(){
        tbl_ViewBookings.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("customerNic"));
        tbl_ViewBookings.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tbl_ViewBookings.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("bookingId"));
        tbl_ViewBookings.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("roomId"));

        try {
            List<CustomDTO> bookings = manageBookingBO.viewBookings();
            ObservableList<CustomDTO>bookingDTOS= FXCollections.observableArrayList(bookings);
            ObservableList<ViewBookingsTM>bookingsTM=FXCollections.observableArrayList();

            for (CustomDTO bookingDTO : bookingDTOS) {
                bookingsTM.add(new ViewBookingsTM(bookingDTO.getCustomer_nic(),bookingDTO.getCustomer_name(),
                        bookingDTO.getBooking_id(),bookingDTO.getRoom_id()));
            }


            tbl_ViewBookings.setItems(bookingsTM);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }



    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        try {
            Parent dashBoard = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/DashBoardForm.fxml"));
            Scene dashBoardScene = new Scene(dashBoard);
            Stage primaryStage= (Stage) viewBookings.getScene().getWindow();
            primaryStage.setScene(dashBoardScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle(" Room Reservation System ");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }



}
