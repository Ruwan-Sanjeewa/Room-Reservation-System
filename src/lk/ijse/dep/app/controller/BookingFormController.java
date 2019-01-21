package lk.ijse.dep.app.controller;

import com.jfoenix.controls.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import lk.ijse.dep.app.business.BOFactory;
import lk.ijse.dep.app.business.custom.*;
import lk.ijse.dep.app.db.DBConnection;
import lk.ijse.dep.app.dto.*;
import lk.ijse.dep.app.view.util.AvailableRoomTM;
import lk.ijse.dep.app.view.util.BookingDetailTM;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookingFormController {
    public Label lbl_Total;
    @FXML
    private TableView<BookingDetailTM> tbl_RoomDetail;
    @FXML
    private JFXButton btn_Search;
    @FXML
    private JFXCheckBox chb_ChangeSearch;
    @FXML
    private JFXDatePicker txt_CheckIn;
    @FXML
    private JFXDatePicker txt_CheckOut;
    @FXML
    private JFXComboBox <RoomTypeDTO>cmb_RoomType;
    @FXML
    private TableView <AvailableRoomTM>tbl_AvailableRoom;
    @FXML
    private JFXButton btn_Add;
    @FXML
    private JFXDatePicker txt_CheckOutDate;
    @FXML
    private JFXDatePicker txt_CheckInDate;
    @FXML
    private JFXTextField txt_RoomType;
    @FXML
    private JFXTextField txt_RoomNo;
    @FXML
    private JFXComboBox <MealTypeDTO>cmb_MealType;
    @FXML
    private AnchorPane booking;
    @FXML
    private JFXTextField txt_CustomerName;
    @FXML
    private JFXTextField txt_Address;
    @FXML
    private JFXTextField txt_Telephone;
    @FXML
    private JFXTextField txt_CustomerNic;
    @FXML
    private JFXTextField txt_BookingId;
    @FXML
    private JFXTextField txt_Duration;




    private ManageBookingBO manageBookingBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKING_BO);
    private ManageMealTypeBO manageMealTypeBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.MEAL_TYPE_BO);
    private  ManageRoomTypeBO manageRoomTypeBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_ROOM_TYPE_BO);
    private ManageAvailableRoomsBO manageAvailableRoomsBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.MANAGE_AVAILABLE_ROOMS);


    private String roomNo;
    private String roomType;
    private String mealTypeId;
    private String mealTypeName;
    private int duration;

    public void initialize(){
        lbl_Total.setText("0.00");
        cmb_RoomType.setDisable(true);
        txt_CheckIn.setDisable(true);
        txt_CheckOut.setDisable(true);
        btn_Search.setDisable(true);

        txt_BookingId.setText(generateBookingId());
        txt_CheckInDate.setValue(DashBoardFormController.checkIn);
        txt_CheckOutDate.setValue(DashBoardFormController.checkOut);

        Period period = Period.between(DashBoardFormController.checkIn, DashBoardFormController.checkOut);
         duration = period.getDays();
        txt_Duration.setText(String.valueOf(duration));


        txt_RoomNo.setEditable(false);
        txt_RoomType.setEditable(false);
        txt_BookingId.setEditable(false);
        txt_CheckInDate.setEditable(false);
        txt_CheckOutDate.setEditable(false);
        txt_Duration.setEditable(false);

        tbl_RoomDetail.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("roomNo"));
        tbl_RoomDetail.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("roomType"));
        tbl_RoomDetail.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("mealTypeId"));
        tbl_RoomDetail.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("checkIn"));
        tbl_RoomDetail.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("checkOut"));
        tbl_RoomDetail.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("duration"));
        tbl_RoomDetail.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("totalPerDay"));
        tbl_RoomDetail.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("total"));


        tbl_AvailableRoom.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("room_no"));
        tbl_AvailableRoom.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("room_type"));

        List<CustomDTO> availableRooms = DashBoardFormController.availableRooms;
        ObservableList<CustomDTO>availableRoomsList= FXCollections.observableArrayList(availableRooms);
        ObservableList<AvailableRoomTM>availableRoomTM=FXCollections.observableArrayList();

        for (CustomDTO customDTO : availableRoomsList) {
            availableRoomTM.add(new AvailableRoomTM(customDTO.getRoom_no(),customDTO.getRoom_type()));
        }

        tbl_AvailableRoom.setItems(availableRoomTM);


        try {
            List<MealTypeDTO> mealTypes = manageMealTypeBO.getMealTypes();
            for (MealTypeDTO mealType : mealTypes) {
                cmb_MealType.getItems().add(mealType);
            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


        cmb_MealType.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<MealTypeDTO>() {
            @Override
            public void changed(ObservableValue<? extends MealTypeDTO> observable, MealTypeDTO oldValue, MealTypeDTO selectedItem) {
                if(selectedItem==null){
                    return;
                }
                mealTypeId=selectedItem.getMeal_type_id();
                mealTypeName = selectedItem.getMeal_type_name();

            }
        });


        tbl_AvailableRoom.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<AvailableRoomTM>() {
            @Override
            public void changed(ObservableValue<? extends AvailableRoomTM> observable, AvailableRoomTM oldValue, AvailableRoomTM selectedItem) {
                    if(selectedItem==null){
                        return;
                    }

                    txt_RoomNo.setText(selectedItem.getRoom_no());
                    txt_RoomType.setText(selectedItem.getRoom_type());



            }
        });

           chb_ChangeSearch.selectedProperty().addListener(new ChangeListener<Boolean>() {
               @Override
               public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                   if(newValue==true) {
                       cmb_RoomType.setDisable(false);
                       txt_CheckIn.setDisable(false);
                       txt_CheckOut.setDisable(false);
                       btn_Search.setDisable(false);
                   }
                   else {
                       cmb_RoomType.setDisable(true);
                       txt_CheckIn.setDisable(true);
                       txt_CheckOut.setDisable(true);
                       btn_Search.setDisable(true);

                   }
               }
           });


        try {
            List<RoomTypeDTO> roomTypes = manageRoomTypeBO.getRoomTypes();
            for (RoomTypeDTO type : roomTypes) {
                cmb_RoomType.getItems().add(type);
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
    public void btnBack_OnAction(ActionEvent actionEvent) {

        try {
            Parent dashboard = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/DashboardForm.fxml"));
            Scene dashboardScene = new Scene(dashboard);
            Stage primaryStage= (Stage) booking.getScene().getWindow();
            primaryStage.setScene(dashboardScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle("DashBoard");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
    }






    @FXML
    private void btnBook_OnAction(ActionEvent actionEvent) {
        String customerName = txt_CustomerName.getText();
        String customerNic = txt_CustomerNic.getText();
        String telephone = txt_Telephone.getText();
        String address = txt_Address.getText();

        if(customerName.trim().isEmpty()){
            errorMsg("Customer Name cannot be empty");
            txt_CustomerName.requestFocus();
            return;
        }

        if(customerNic.trim().isEmpty()){
            errorMsg("Customer NIC cannot be empty");
            txt_CustomerNic.requestFocus();
            return;
        }
        if(telephone.trim().isEmpty()){
            errorMsg("Telephone Number cannot be empty");
            txt_Telephone.requestFocus();
            return;
        }
        if(address.trim().isEmpty()){
            errorMsg("Address cannot be empty");
            txt_Address.requestFocus();
            return;
        }

        if(!customerNic.matches("^\\d{9}[vV]$")){
            errorMsg("Invalid NIC");
            txt_CustomerNic.clear();
            txt_CustomerNic.requestFocus();
            return;
        }
        if(!telephone.matches("^\\d{10}$")){
            errorMsg("Invalid Telephone Number");
            txt_Telephone.clear();
            txt_Telephone.requestFocus();
            return;
        }


        ObservableList<BookingDetailTM> items = tbl_RoomDetail.getItems();

        if(items.size()==0){
            errorMsg("Add a room first");
            return;
        }
        ArrayList<RoomBookingDetailDTO>roomBookingDetailDTOS=new ArrayList<>();

        for (BookingDetailTM item : items) {

            roomBookingDetailDTOS.add(new RoomBookingDetailDTO(txt_BookingId.getText(),item.getRoomNo(),item.getMealTypeId(),
                    item.getCheckIn(),item.getCheckOut(),item.getDuration()));
        }


        try {
            manageBookingBO.createBooking(new BookingDTO2(customerNic,customerName,telephone,address,
                    txt_BookingId.getText(),Double.parseDouble(lbl_Total.getText()), roomBookingDetailDTOS));
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        txt_BookingId.setText(generateBookingId());

        fullReset();

        new Alert(Alert.AlertType.INFORMATION,"Rooms Booked Successfully").showAndWait();


    }






    public String generateBookingId(){
        try {
            List<BookingDTO> bookings = manageBookingBO.getBookings();
            String id="B";
            int size=bookings.size()+1;
            return id+size ;

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        return null;
    }





    @FXML
    private void btnAdd_OnAction(ActionEvent actionEvent) {
        String bookingId = txt_BookingId.getText();
        String roomNo = txt_RoomNo.getText();
        String roomType = txt_RoomType.getText();
        LocalDate checkIn = txt_CheckInDate.getValue();
        LocalDate checkOut = txt_CheckOutDate.getValue();



        if(bookingId.trim().isEmpty()){
            errorMsg("Booking ID cannot be empty");
            return;
        }

        if(roomNo.trim().isEmpty()){
            errorMsg("Room Number cannot be empty");
            return;
        }
        if(roomType.trim().isEmpty()){
            errorMsg("Room Type cannot be empty");
            return;
        }
        if(cmb_MealType.getSelectionModel().isEmpty()){
            errorMsg("Select Meal Type");
            return;
        }
        if(checkIn.toString().trim().isEmpty()){
            errorMsg("Select Check In date");
            return;
        }

        if(checkOut.toString().trim().isEmpty()){
            errorMsg("Select Check Out date");
            return;
        }

        if(txt_Duration.getText().trim().isEmpty()){
            errorMsg("Duration cannot be empty");
            return;
        }

        ObservableList<BookingDetailTM> items = tbl_RoomDetail.getItems();
        for (BookingDetailTM item : items) {
            if(item.getRoomNo().equals(roomNo)){
                errorMsg("Previously added room ");
                return;

            }
        }


        Double totalPerDay=0.00;
        try {
            MealTypeDTO mealType = manageMealTypeBO.findMealType(mealTypeId);
            Double mealTypePrice =mealType.getPrice();
            RoomTypeDTO roomType1 = manageRoomTypeBO.findRoomType(roomType);
            Double roomTypePrice=roomType1.getPrice();
            totalPerDay=roomTypePrice+mealTypePrice;

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        Double total=duration*totalPerDay;


        tbl_RoomDetail.getItems().add(new BookingDetailTM(roomNo,roomType,mealTypeId,checkIn,checkOut,duration,totalPerDay,total));




        AvailableRoomTM selectedItem = tbl_AvailableRoom.getSelectionModel().getSelectedItem();
        tbl_AvailableRoom.getItems().remove(selectedItem);
        reset();

        Double bookingTotal= 0.00;
        ObservableList<BookingDetailTM> items1 = tbl_RoomDetail.getItems();
        for (BookingDetailTM bookingDetailTM : items1) {
            bookingTotal+=bookingDetailTM.getTotal();
        }
        lbl_Total.setText(String.valueOf(bookingTotal));


    }

    @FXML
    private void btnRemove_OnAction(ActionEvent actionEvent) {
        BookingDetailTM selectedItem = tbl_RoomDetail.getSelectionModel().getSelectedItem();
        if(tbl_RoomDetail.getSelectionModel().isEmpty()){
            errorMsg("Select a room first");
            return;
        }

        Optional<ButtonType> buttonType = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure to remove this room booking ", ButtonType.YES, ButtonType.NO).showAndWait();
        if(buttonType.get()==ButtonType.YES){
            tbl_RoomDetail.getItems().remove(selectedItem);
        }

        reset();
        tbl_AvailableRoom.getSelectionModel().clearSelection();
        tbl_AvailableRoom.getItems().add(new AvailableRoomTM(selectedItem.getRoomNo(),selectedItem.getRoomType()));
        tbl_AvailableRoom.refresh();
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

        tbl_AvailableRoom.getItems().removeAll();
        try {
            List<CustomDTO> availableRooms = manageAvailableRoomsBO.getAvailableRooms(txt_CheckIn.getValue(), txt_CheckOut.getValue(), roomType);
            ObservableList<CustomDTO>availableRoomsList= FXCollections.observableArrayList(availableRooms);
            ObservableList<AvailableRoomTM>availableRoomTM=FXCollections.observableArrayList();

            for (CustomDTO customDTO : availableRoomsList) {

                ObservableList<BookingDetailTM> items = tbl_RoomDetail.getItems();
                for (BookingDetailTM item : items) {
                    if(!item.getRoomNo().equals(customDTO.getRoom_no())){
                        availableRoomTM.add(new AvailableRoomTM(customDTO.getRoom_no(),customDTO.getRoom_type()));
                    }
                }


            }

            tbl_AvailableRoom.setItems(availableRoomTM);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        txt_CheckInDate.setValue(txt_CheckIn.getValue());
        txt_CheckOutDate.setValue(txt_CheckOut.getValue());

        Period period = Period.between(txt_CheckIn.getValue(),txt_CheckOut.getValue() );
        int duration = period.getDays();
        txt_Duration.setText(String.valueOf(duration));
    }

    private void errorMsg(String msg){
        new Alert(Alert.AlertType.ERROR,msg, ButtonType.OK).showAndWait();
    }


    private void reset(){

        txt_RoomNo.clear();
        txt_RoomType.clear();
        cmb_MealType.getSelectionModel().clearSelection();
    }

    private void fullReset(){
        txt_CustomerName.clear();
        txt_CustomerNic.clear();
        txt_Telephone.clear();
        txt_Address.clear();
        txt_CheckInDate.setValue(null);
        txt_CheckOutDate.setValue(null);
        txt_Duration.clear();


        tbl_AvailableRoom.getItems().removeAll();
        tbl_RoomDetail.getItems().removeAll();
    }
}

