package lk.ijse.dep.app.view.util;

public class ViewBookingsTM {

    private String customerNic;
    private String customerName;
    private String bookingId;
    private String roomId;

    public ViewBookingsTM() {
    }


    public ViewBookingsTM(String customerNic, String customerName, String bookingId, String roomId) {
        this.setCustomerNic(customerNic);
        this.setCustomerName(customerName);
        this.setBookingId(bookingId);
        this.setRoomId(roomId);
    }


    public String getCustomerNic() {
        return customerNic;
    }

    public void setCustomerNic(String customerNic) {
        this.customerNic = customerNic;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }


    @Override
    public String toString() {
        return "ViewBookingsTM{" +
                "customerNic='" + customerNic + '\'' +
                ", customerName='" + customerName + '\'' +
                ", bookingId='" + bookingId + '\'' +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
