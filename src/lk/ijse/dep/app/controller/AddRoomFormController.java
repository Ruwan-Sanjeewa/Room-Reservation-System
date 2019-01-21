package lk.ijse.dep.app.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.app.business.BOFactory;
import lk.ijse.dep.app.business.custom.ManageBookingBO;
import lk.ijse.dep.app.business.custom.ManageRoomBO;
import lk.ijse.dep.app.business.custom.ManageRoomTypeBO;
import lk.ijse.dep.app.dto.RoomDTO;
import lk.ijse.dep.app.dto.RoomTypeDTO;
import lk.ijse.dep.app.entity.RoomType;
import lk.ijse.dep.app.view.util.RoomTM;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRoomFormController {
    @FXML
    private JFXComboBox <RoomTypeDTO>cmb_RoomType;
    @FXML
    private JFXButton btn_add;
    @FXML
    private AnchorPane addRooms;
    @FXML
    private JFXTextField txt_RoomNo;
    @FXML
    private TableView<RoomTM> tbl_Rooms;

    private String roomType;
    private ManageRoomBO manageRoomBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM_BO);
    private ManageRoomTypeBO manageRoomTypeBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ROOM_TYPE_BO);

    public void initialize(){
        txt_RoomNo.requestFocus();

        tbl_Rooms.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_no"));
        tbl_Rooms.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("room_type"));


        try {
            List<RoomDTO> rooms = manageRoomBO.getRooms();
            ObservableList<RoomDTO>roomList= FXCollections.observableArrayList(rooms);
            ObservableList<RoomTM>roomTM=FXCollections.observableArrayList();

            for (RoomDTO roomDTO : roomList) {
                roomTM.add(new RoomTM(roomDTO.getRoom_no(),roomDTO.getRoom_type()));
            }

            tbl_Rooms.setItems(roomTM);

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }




        try {
            List<RoomTypeDTO> roomTypes = manageRoomTypeBO.getRoomTypes();
            for (RoomTypeDTO roomType : roomTypes) {
                cmb_RoomType.getItems().add(roomType);
            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        cmb_RoomType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RoomTypeDTO>() {
            @Override
            public void changed(ObservableValue<? extends RoomTypeDTO> observable, RoomTypeDTO oldValue, RoomTypeDTO selectedItem1) {
                if(selectedItem1==null){
                    return;
                }
                roomType= selectedItem1.getRoom_type_name();
            }
        });

        tbl_Rooms.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RoomTM>() {
            @Override
            public void changed(ObservableValue<? extends RoomTM> observable, RoomTM oldValue, RoomTM selectedItem) {
                if(selectedItem==null){
                    return ;
                }


                txt_RoomNo.setText(selectedItem.getRoom_no());



                txt_RoomNo.setDisable(true);
                btn_add.setText("     Edit");

            }
        });



    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {

        try {
            Parent rooms = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/RoomsForm.fxml"));
            Scene roomsScene = new Scene(rooms);
            Stage primaryStage= (Stage) addRooms.getScene().getWindow();
            primaryStage.setScene(roomsScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Rooms");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {
        String roomNo= txt_RoomNo.getText();


        if(roomNo.trim().isEmpty()){
            errorMsg("Room Number cannot be empty");
            txt_RoomNo.requestFocus();
            return;
        }


        if(tbl_Rooms.getSelectionModel().isEmpty()) {

            ObservableList<RoomTM> items = tbl_Rooms.getItems();
            for (RoomTM item : items) {
                if(item.getRoom_no().equals(roomNo)){
                    errorMsg("This Room number has been entered previously");
                    txt_RoomNo.clear();
                    txt_RoomNo.requestFocus();
                    return;
                }
            }

            RoomDTO roomDTO = new RoomDTO(roomNo, roomType);
            try {
                manageRoomBO.saveRoom(roomDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }
            RoomTM roomTM = new RoomTM(roomNo, roomType);
            tbl_Rooms.getItems().add(roomTM);
            reset();

        }
        else{
            RoomTM selectedItem = tbl_Rooms.getSelectionModel().getSelectedItem();
            selectedItem.setRoom_type(roomType);


            RoomDTO roomDTO = new RoomDTO(roomNo, roomType);
            try {
                manageRoomBO.updateRoom(roomDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            reset();
            tbl_Rooms.refresh();
            tbl_Rooms.getSelectionModel().clearSelection();
            btn_add.setText("    Add");
            txt_RoomNo.setDisable(false);

        }



    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {
        RoomTM selectedItem = tbl_Rooms.getSelectionModel().getSelectedItem();
        if(tbl_Rooms.getSelectionModel().isEmpty()){
            errorMsg("Select a Room first");
            return;
        }

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Room?", ButtonType.YES, ButtonType.NO).showAndWait();
        if(buttonType.get()==ButtonType.YES){
            RoomDTO roomDTO = new RoomDTO(selectedItem.getRoom_no(),selectedItem.getRoom_type());
            try {
                manageRoomBO.deleteRoom(roomDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            tbl_Rooms.getItems().remove(selectedItem);
            reset();
            tbl_Rooms.refresh();
            tbl_Rooms.getSelectionModel().clearSelection();
            btn_add.setText("    Add");
            txt_RoomNo.setDisable(false);
        }
    }

    private void errorMsg(String msg){
        new Alert(Alert.AlertType.ERROR,msg, ButtonType.OK).showAndWait();
    }

    private void reset(){
        txt_RoomNo.clear();
        cmb_RoomType.setValue(null);
        txt_RoomNo.requestFocus();
    }
}
