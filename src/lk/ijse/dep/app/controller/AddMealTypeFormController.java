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
import lk.ijse.dep.app.business.custom.ManageMealTypeBO;
import lk.ijse.dep.app.dto.MealTypeDTO;
import lk.ijse.dep.app.view.util.MealTypeTM;


import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddMealTypeFormController {
    @FXML
    private JFXButton btn_Add;
    @FXML
    private AnchorPane addMealType;
    @FXML
    private JFXTextField txt_MealTypeId;
    @FXML
    private JFXTextField txt_MealTypeName;
    @FXML
    private JFXTextField txt_Price;
    @FXML
    private TableView <MealTypeTM> tbl_MealType;

    private ManageMealTypeBO manageMealTypeBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.MEAL_TYPE_BO);

    public void initialize(){
        tbl_MealType.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("meal_type_id"));
        tbl_MealType.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("meal_type_name"));
        tbl_MealType.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("price"));

        try {
            List<MealTypeDTO> mealTypes = manageMealTypeBO.getMealTypes();
            ObservableList<MealTypeDTO>mealTypeList= FXCollections.observableArrayList(mealTypes);
            ObservableList<MealTypeTM>mealTypeTM=FXCollections.observableArrayList();

            for (MealTypeDTO mealTypeDTO : mealTypeList) {
                mealTypeTM.add(new MealTypeTM(mealTypeDTO.getMeal_type_id(),mealTypeDTO.getMeal_type_name(),
                        mealTypeDTO.getPrice()));
            }

            tbl_MealType.setItems(mealTypeTM);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        tbl_MealType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MealTypeTM>() {
            @Override
            public void changed(ObservableValue<? extends MealTypeTM> observable, MealTypeTM oldValue, MealTypeTM selectedItem) {
                if(selectedItem==null){
                    return ;
                }
                txt_MealTypeId.setText(selectedItem.getMeal_type_id());
                txt_MealTypeName.setText(selectedItem.getMeal_type_name());
                txt_Price.setText(String.valueOf(selectedItem.getPrice()));


                txt_MealTypeId.setDisable(true);
                btn_Add.setText("     Edit");

            }
        });


    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        try {
            Parent dashBoard = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/DashBoardForm.fxml"));
            Scene dashBoardScene = new Scene(dashBoard);
            Stage primaryStage= (Stage) addMealType.getScene().getWindow();
            primaryStage.setScene(dashBoardScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("Room Reservation System ");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {
        String mealTypeId = txt_MealTypeId.getText();
        String mealTypeName = txt_MealTypeName.getText();
        String price = txt_Price.getText();

        if(mealTypeId.trim().isEmpty()){
            errorMsg("Meal type ID cannot be empty");
            txt_MealTypeId.requestFocus();
            return;
        }
        if(mealTypeName.trim().isEmpty()){
            errorMsg("Meal type name cannot be empty");
            txt_MealTypeName.requestFocus();
            return;
        }
        if(price.trim().isEmpty()){
            errorMsg("Meal type price cannot be empty");
            txt_Price.requestFocus();
            return;
        }
        if(!price.matches("^\\d*\\.\\d+|\\d+\\.\\d*$")) {
            errorMsg("Invalid Price ");
            txt_Price.clear();
            txt_Price.requestFocus();
            return;
        }

        if(tbl_MealType.getSelectionModel().isEmpty()) {
            ObservableList<MealTypeTM> items = tbl_MealType.getItems();
            for (MealTypeTM item : items) {
                if(item.getMeal_type_id().equals(mealTypeId)){
                    errorMsg("This Meal type ID has been entered previously ");
                    txt_MealTypeId.clear();
                    txt_MealTypeId.requestFocus();
                    return;
                }
            }

            MealTypeDTO mealTypeDTO = new MealTypeDTO(mealTypeId, mealTypeName, Double.parseDouble(price));
            try {
                manageMealTypeBO.saveMealType(mealTypeDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            MealTypeTM mealTypeTM = new MealTypeTM(mealTypeId, mealTypeName, Double.parseDouble(price));
            tbl_MealType.getItems().add(mealTypeTM);
            reset();
        }
        else{
            MealTypeTM selectedItem = tbl_MealType.getSelectionModel().getSelectedItem();
            selectedItem.setMeal_type_name(mealTypeName);
            selectedItem.setPrice(Double.valueOf(price));

            MealTypeDTO mealTypeDTO = new MealTypeDTO(mealTypeId, mealTypeName, Double.parseDouble(price));
            try {
                manageMealTypeBO.updateMealType(mealTypeDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            reset();
            tbl_MealType.refresh();
            tbl_MealType.getSelectionModel().clearSelection();
            btn_Add.setText("    Add");
            txt_MealTypeId.setDisable(false);
        }

    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {
        MealTypeTM selectedItem = tbl_MealType.getSelectionModel().getSelectedItem();
        if(tbl_MealType.getSelectionModel().isEmpty()){
            errorMsg("Select a Meal Type first");
            return;
        }

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to delete this Meal Type?", ButtonType.YES, ButtonType.NO).showAndWait();
        if(buttonType.get()==ButtonType.YES){
            MealTypeDTO mealTypeDTO = new MealTypeDTO(txt_MealTypeId.getText(),txt_MealTypeName.getText(),
                    Double.parseDouble(txt_Price.getText()));
            try {
                manageMealTypeBO.deleteMealType(mealTypeDTO);
            } catch (Exception e) {
                Logger.getLogger("").log(Level.SEVERE,null,e);
            }

            tbl_MealType.getItems().remove(selectedItem);
            reset();
            tbl_MealType.refresh();
            tbl_MealType.getSelectionModel().clearSelection();
            btn_Add.setText("    Add");
            txt_MealTypeId.setDisable(false);
        }
    }

    private void errorMsg(String msg){
        new Alert(Alert.AlertType.ERROR,msg, ButtonType.OK).showAndWait();

    }

    private void reset(){
        txt_MealTypeId.clear();
        txt_MealTypeName.clear();
        txt_Price.clear();
        txt_MealTypeId.requestFocus();
    }
}
