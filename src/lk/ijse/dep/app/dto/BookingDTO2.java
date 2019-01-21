package lk.ijse.dep.app.dto;

import java.util.ArrayList;

public class BookingDTO2 {
    private String customer_nic;
    private String customer_name;
    private String telephone;
    private String address;
    private String booking_id;
    private Double total;
    private ArrayList<RoomBookingDetailDTO>roomBookingDetailDTOS=new ArrayList<>();

    public BookingDTO2() {
    }

    public BookingDTO2(String customer_nic, String customer_name, String telephone, String address, String booking_id, Double total, ArrayList<RoomBookingDetailDTO> roomBookingDetailDTOS) {
        this.setCustomer_nic(customer_nic);
        this.setCustomer_name(customer_name);
        this.setTelephone(telephone);
        this.setAddress(address);
        this.setBooking_id(booking_id);
        this.setTotal(total);
        this.setRoomBookingDetailDTOS(roomBookingDetailDTOS);
    }


    public String getCustomer_nic() {
        return customer_nic;
    }

    public void setCustomer_nic(String customer_nic) {
        this.customer_nic = customer_nic;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public ArrayList<RoomBookingDetailDTO> getRoomBookingDetailDTOS() {
        return roomBookingDetailDTOS;
    }

    public void setRoomBookingDetailDTOS(ArrayList<RoomBookingDetailDTO> roomBookingDetailDTOS) {
        this.roomBookingDetailDTOS = roomBookingDetailDTOS;
    }


    @Override
    public String toString() {
        return "BookingDTO2{" +
                "customer_nic='" + customer_nic + '\'' +
                ", customer_name='" + customer_name + '\'' +
                ", telephone=" + telephone +
                ", address='" + address + '\'' +
                ", booking_id='" + booking_id + '\'' +
                ", total='" + total + '\'' +
                ", roomBookingDetailDTOS=" + roomBookingDetailDTOS +
                '}';
    }
}
