package lk.ijse.dep.app.controller;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep.app.business.BOFactory;
import lk.ijse.dep.app.business.custom.ManageBookingBO;
import lk.ijse.dep.app.business.custom.ManagePaymentBO;
import lk.ijse.dep.app.db.DBConnection;
import lk.ijse.dep.app.dto.BookingDTO;
import lk.ijse.dep.app.dto.PaymentDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentFormController {
    @FXML
    private Label lbl_Status;
    @FXML
    private JFXTextField txt_BookingId;
    @FXML
    private JFXTextField txt_PaymentId;
    @FXML
    private JFXTextField txt_TotalPrice;
    @FXML
    private JFXTextField txt_Advance;
    @FXML
    private JFXTextField txt_Balance;
    @FXML
    private JFXRadioButton rd_Advance;
    @FXML
    private JFXRadioButton rd_Full;
    @FXML
    private AnchorPane payment;

    private ManagePaymentBO managePaymentBO= BOFactory.getInstance().getBO(BOFactory.BOTypes.PAYMENT_BO);
    private ManageBookingBO manageBookingBO=BOFactory.getInstance().getBO(BOFactory.BOTypes.BOOKING_BO);



    private String status;
    public void initialize(){
        final ToggleGroup toggleGroup = new ToggleGroup();
        rd_Advance.setToggleGroup(toggleGroup);
        rd_Full.setToggleGroup(toggleGroup);

        txt_PaymentId.setText(generatePaymentId());

    }

    @FXML
    private void btnBack_OnAction(ActionEvent actionEvent) {
        try {
            Parent dashBoard = FXMLLoader.load(this.getClass().getResource("/lk/ijse/dep/app/view/DashBoardForm.fxml"));
            Scene dashBoardScene = new Scene(dashBoard);
            Stage primaryStage= (Stage) payment.getScene().getWindow();
            primaryStage.setScene(dashBoardScene);
            primaryStage.centerOnScreen();
            primaryStage.setResizable(false);
            primaryStage.setTitle(" Room Reservation  System");
            primaryStage.show();
        } catch (IOException e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

    }

    @FXML
    private void btnPay_OnAction(ActionEvent actionEvent) {
        String bookingId = txt_BookingId.getText();
        String paymentId = txt_PaymentId.getText();
        String totalPrice = txt_TotalPrice.getText();
        String advance = txt_Advance.getText();
        String balance = txt_Balance.getText();

        if(bookingId.trim().isEmpty()){
            errorMsg("Booking ID cannot be empty");
            txt_BookingId.requestFocus();
            return;
        }

        if(paymentId.trim().isEmpty()){
            errorMsg("Payment ID cannot be empty");
            txt_BookingId.requestFocus();
            return;
        }

        if(totalPrice.trim().isEmpty()){
            errorMsg("Total price cannot be empty");
            txt_TotalPrice.requestFocus();
            return;
        }
        if(advance.trim().isEmpty()){
            errorMsg("Advance cannot be empty");
            txt_Advance.requestFocus();
            return;
        }
        if(balance.trim().isEmpty()){
            errorMsg("Balance cannot be empty");
            txt_Balance.requestFocus();
            return;
        }

        status="";
        if(rd_Advance.isSelected()){
            status="Advance";

        }
        else if(rd_Full.isSelected()){
            status="Full";

        }

        if(!rd_Full.isSelected() && !rd_Advance.isSelected()){
            errorMsg("Select Payment status");
            return;
        }

        if(!totalPrice.matches("^\\d*\\.\\d+|\\d+\\.\\d*$")){
            errorMsg("Invalid Total Price");
            txt_TotalPrice.clear();
            txt_TotalPrice.requestFocus();
            return;
        }

        if(!advance.matches("^\\d*\\.\\d+|\\d+\\.\\d*$")){
            errorMsg("Invalid Advance ");
            txt_Advance.clear();
            txt_Advance.requestFocus();
            return;
        }
        if(!balance.matches("^\\d*\\.\\d+|\\d+\\.\\d*$")){
            errorMsg("Invalid Balance");
            txt_Balance.clear();
            txt_Balance.requestFocus();
            return;
        }


        try {
            PaymentDTO payment = managePaymentBO.findPayment(txt_PaymentId.getText());
            if(payment.getStatus().equals("Full")){

                    errorMsg("This Payment is already fully paid");
                    return;


            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        PaymentDTO paymentDTO = new PaymentDTO(bookingId, paymentId, Double.parseDouble(totalPrice), Double.parseDouble(advance),
                Double.parseDouble(balance), status);
        try {
            if(status=="Advance") {
                managePaymentBO.savePayment(paymentDTO);
            }
            else if(status=="Full"){
                managePaymentBO.updatePayment(paymentDTO);

            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }






        try {
            File file = new File("reports/Bill.jasper");

            JasperReport compiledReport = (JasperReport) JRLoader.loadObject(file);

        HashMap<String, Object> param = new HashMap<>();
        param.put("bookingId",txt_BookingId.getText());
        param.put("paymentId",txt_PaymentId.getText());


        DefaultTableModel dtm = new DefaultTableModel(new Object[]{"total","advance","balance"},0);

        Object[] rowData = {txt_TotalPrice.getText(),txt_Advance.getText(),txt_Balance.getText()};
        dtm.addRow(rowData);

            JasperPrint filledReport = JasperFillManager.fillReport(compiledReport, param,  new JRTableModelDataSource(dtm));
            JasperViewer.viewReport(filledReport,false);
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }

        reset();

    }

    private  String  generatePaymentId(){
        try {
            List<PaymentDTO> payments = managePaymentBO.getPayments();
            int size=payments.size()+1;
            String id ="P"+size;
            return id;
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }
        return null;
    }
    private void errorMsg(String msg){
        new Alert(Alert.AlertType.ERROR,msg, ButtonType.OK).showAndWait();
    }

    @FXML
    private void txtBookingId_OnAction(ActionEvent actionEvent) {


        try {
            BookingDTO booking = manageBookingBO.findBooking(txt_BookingId.getText());
            txt_TotalPrice.setText(String.valueOf(booking.getTotal()));
            List<PaymentDTO> payments = managePaymentBO.getPayments();
            for (PaymentDTO paymentDTO : payments) {
                if(paymentDTO.getBooking_id().equals(txt_BookingId.getText())){
                    txt_PaymentId.setText(paymentDTO.getPayment_id());
                    txt_TotalPrice.setText(String.valueOf(paymentDTO.getTotal_price()));
                    txt_Advance.setText(String.valueOf(paymentDTO.getAdvance()));
                    txt_Balance.setText(String.valueOf(paymentDTO.getBalance()));
                }
                else if(!paymentDTO.getBooking_id().equals(txt_BookingId.getText())){
                   txt_TotalPrice.setText(String.valueOf(paymentDTO.getTotal_price()));
                }
            }

        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


        try {

            PaymentDTO payment = managePaymentBO.findPayment(txt_PaymentId.getText());

            if(payment!=null) {
                lbl_Status.setText(payment.getStatus()+" paid");
            }
        } catch (Exception e) {
            Logger.getLogger("").log(Level.SEVERE,null,e);
        }


    }
    @FXML
    private void txtAdvance_OnAction(ActionEvent actionEvent) {
        String total = txt_TotalPrice.getText();
        String advance = txt_Advance.getText();

        Double balance =Double.parseDouble(total)-Double.parseDouble(advance);
        txt_Balance.setText(String.valueOf(balance));

    }

    private void reset(){
        lbl_Status.setText("");
        rd_Advance.setSelected(false);
        rd_Full.setSelected(false);
        txt_BookingId.clear();
        txt_PaymentId.clear();
        txt_TotalPrice.clear();
        txt_Advance.clear();
        txt_Balance.clear();

    }
}
