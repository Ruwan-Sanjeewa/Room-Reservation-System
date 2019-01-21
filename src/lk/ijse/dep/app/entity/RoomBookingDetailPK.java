package lk.ijse.dep.app.entity;

public class RoomBookingDetailPK {
    private String booking_id;
    private String room_no;

    public RoomBookingDetailPK() {
    }

    public RoomBookingDetailPK(String booking_id, String room_no) {
        this.setBooking_id(booking_id);
        this.setRoom_no(room_no);
    }


    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getRoom_no() {
        return room_no;
    }

    public void setRoom_no(String room_no) {
        this.room_no = room_no;
    }

    @Override
    public String toString() {
        return "RoomBookingDetailPK{" +
                "booking_id='" + booking_id + '\'' +
                ", room_no='" + room_no + '\'' +
                '}';
    }
}
