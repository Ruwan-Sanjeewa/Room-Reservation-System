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
import lk.ijse.dep.app.business.custom.ManageRoomBO;
import lk.ijse.dep.app.business.custom.ManageRoomTypeBO;
import lk.ijse.dep.app.dto.RoomDTO;
import lk.ijse.dep.app.dto.RoomTypeDTO;
import lk.ijse.dep.app.view.util.RoomTM;
import lk.ijse.dep.app.view.util.RoomTypeTM;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RoomDetailFormController {
    @FXML
    private AnchorPane roomDetails;
    @FXML
    private TableView <RoomTypeTM>tbl_ViewRooms;

    private ManageRoomTypeBO manageRoomTypeBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ROOM_TYPE_BO);

    public  void  initialize(){
        tbl_ViewRooms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_type_name"));
        tbl_ViewRooms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_ViewRooms.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            List<RoomTypeDTO> roomTypes = manageRoomTypeBO.getRoomTypes();
            ObservableList<RoomTypeDTO>roomList= FXCollections.observableArrayList(roomTypes);
            ObservableList<RoomTypeTM>roomTM=FXCollections.observableArrayList();
            for (RoomTypeDTO room: roomList) {
                roomTM.add(new RoomTypeTM(room.getRoom_type_name(),room.getDescription(),room.getPrice()));
            }

            tbl_ViewRooms.setItems(roomTM);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        try {
            Parent rooms = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/RoomsForm.fxml"));
            Scene roomsScene = new Scene(rooms);
            Stage primaryStage= (Stage) roomDetails.getScene().getWindow();
            primaryStage.setScene(roomsScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle(" Rooms");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }
}
