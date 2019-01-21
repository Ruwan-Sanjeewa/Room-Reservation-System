package lk.ijse.dep.app.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.dep.app.business.custom.ManageRoomBO;
import lk.ijse.dep.app.business.custom.ManageRoomTypeBO;
import lk.ijse.dep.app.dto.RoomDTO;
import lk.ijse.dep.app.dto.RoomTypeDTO;
import lk.ijse.dep.app.view.util.RoomTypeTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddRoomTypeFormController {
    @FXML
    private AnchorPane roomType;
    @FXML
    private JFXTextField txt_RoomTypeName;
    @FXML
    private JFXTextField txt_Description;
    @FXML
    private JFXTextField txt_Price;
    @FXML
    private TableView <RoomTypeTM>tbl_RoomType;
    @FXML
    private JFXButton btn_add;

    private ManageRoomTypeBO manageRoomTypeBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ROOM_TYPE_BO);
    private ManageRoomBO manageRoomBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.ROOM_BO);
    public void initialize(){
        tbl_RoomType.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_type_name"));
        tbl_RoomType.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tbl_RoomType.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            List<RoomTypeDTO> roomTypes = manageRoomTypeBO.getRoomTypes();
            ObservableList<RoomTypeDTO>roomTypeList= FXCollections.observableArrayList(roomTypes);
            ObservableList<RoomTypeTM>roomTypeTM=FXCollections.observableArrayList();

            for (RoomTypeDTO roomTypeDTO : roomTypeList) {
                roomTypeTM.add(new RoomTypeTM(roomTypeDTO.getRoom_type_name(),roomTypeDTO.getDescription(),
                        roomTypeDTO.getPrice()));
            }

            tbl_RoomType.setItems(roomTypeTM);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


        tbl_RoomType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<RoomTypeTM>() {
            @Override
            public void changed(ObservableValue<? extends RoomTypeTM> observable, RoomTypeTM oldValue, RoomTypeTM selectedItem) {
                if(selectedItem==null){
                    return ;
                }
                txt_RoomTypeName.setText(selectedItem.getRoom_type_name());
                txt_Description.setText(selectedItem.getDescription());
                txt_Price.setText(String.valueOf(selectedItem.getPrice()));


                txt_RoomTypeName.setDisable(true);
                btn_add.setText("     Edit");

            }
        });

    }


    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        try {
            Parent rooms = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/RoomsForm.fxml"));
            Scene roomsScene = new Scene(rooms);
            Stage primaryStage= (Stage) roomType.getScene().getWindow();
            primaryStage.setScene(roomsScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Rooms ");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {
        String roomType = txt_RoomTypeName.getText();
        String description = txt_Description.getText();
        String price = txt_Price.getText();

        if(roomType.trim().isEmpty()){
            errorMsg("Room Type type cannot be empty");
            txt_RoomTypeName.requestFocus();
            return;
        }

        if(description.trim().isEmpty()){
            errorMsg("Room Type description cannot be empty");
            txt_Description.requestFocus();
            return;
        }

        if(price.trim().isEmpty()){
            errorMsg("Room Type price cannot be empty");
            txt_Price.requestFocus();
            return;
        }

        if(!price.matches("^\\d*\\.\\d+|\\d+\\.\\d*$")){
            errorMsg("Invalid Price");
            txt_Price.clear();
            txt_Price.requestFocus();
            return;
        }

        if(tbl_RoomType.getSelectionModel().isEmpty()) {
            ObservableList<RoomTypeTM> items = tbl_RoomType.getItems();
            for (RoomTypeTM item : items) {
                if(item.getRoom_type_name().equals(roomType)){
                    errorMsg("This room type has been previously entered ");
                    txt_RoomTypeName.clear();
                    txt_RoomTypeName.requestFocus();
                    return;
                }
            }

            try {
                RoomTypeDTO roomTypeDTO = new RoomTypeDTO(roomType, description, Double.parseDouble(price));
                manageRoomTypeBO.saveRoomType(roomTypeDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            tbl_RoomType.getItems().add(new RoomTypeTM(roomType, description, Double.parseDouble(price)));
            reset();
        }

        else{
            RoomTypeTM selectedItem = tbl_RoomType.getSelectionModel().getSelectedItem();
            selectedItem.setRoom_type_name(roomType);
            selectedItem.setDescription(description);
            selectedItem.setPrice(Double.valueOf(price));

            RoomTypeDTO roomTypeDTO = new RoomTypeDTO(roomType, description, Double.parseDouble(price));
            try {
                manageRoomTypeBO.updateRoomType(roomTypeDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            reset();
            tbl_RoomType.refresh();
            tbl_RoomType.getSelectionModel().clearSelection();
            btn_add.setText("    Add");
            txt_RoomTypeName.setDisable(false);

        }
    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {
        RoomTypeTM selectedItem = tbl_RoomType.getSelectionModel().getSelectedItem();
        if(tbl_RoomType.getSelectionModel().isEmpty()){
            errorMsg("Select a Room Type first");
            return;
        }

        try {
            List<RoomDTO> rooms = manageRoomBO.getRooms();
            for (RoomDTO room : rooms) {
                if(room.getRoom_type().equals(selectedItem.getRoom_type_name())){
                    errorMsg("This RoomType Cannot delete because there are rooms registered from this room type");
                    tbl_RoomType.getSelectionModel().clearSelection();
                    return;
                }
            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Room Type?", ButtonType.YES, ButtonType.NO).showAndWait();
        if(buttonType.get()==ButtonType.YES){
            RoomTypeDTO roomTypeDTO = new RoomTypeDTO(selectedItem.getRoom_type_name(),selectedItem.getDescription(),
                    selectedItem.getPrice());
            try {
                manageRoomTypeBO.deleteRoomType(roomTypeDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            tbl_RoomType.getItems().remove(selectedItem);
            reset();
            tbl_RoomType.refresh();
            tbl_RoomType.getSelectionModel().clearSelection();
            btn_add.setText("    Add");
            txt_RoomTypeName.setDisable(false);
        }
    }

    private void errorMsg(String msg){
        new Alert(Alert.AlertType.ERROR,msg, ButtonType.OK).showAndWait();
    }

    private void reset(){
        txt_RoomTypeName.clear();
        txt_Description.clear();
        txt_Price.clear();
        txt_RoomTypeName.requestFocus();
    }

}
