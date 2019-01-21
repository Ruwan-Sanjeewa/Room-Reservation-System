package lk.ijse.dep.app.entity;

public class CustomEntity {
    private String room_no;
    private String room_type;


    private String customer_nic;
    private String customer_name;
    private String booking_id;
    private String room_id;

    public CustomEntity() {
    }

    public CustomEntity(String room_no, String room_type) {
        this.setRoom_no(room_no);
        this.setRoom_type(room_type);

    }


    public CustomEntity(String customer_nic, String customer_name, String booking_id, String room_id) {
        this.setCustomer_nic(customer_nic);
        this.setCustomer_name(customer_name);
        this.setBooking_id(booking_id);
        this.setRoom_id(room_id);
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
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

    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id;
    }





    @Override
    public String toString() {
        return "CustomEntity{" +
                "room_no='" + room_no + '\'' +
                ", room_type='" + room_type + '\'' +
                '}';
    }


}
