package lk.ijse.dep.app.entity;

public class Booking  extends  SuperEntity{
    private String customer_nic;
    private String booking_id;
    private Double total;

    public Booking() {
    }

    public Booking(String customer_nic, String booking_id, Double total) {
        this.setCustomer_nic(customer_nic);
        this.setBooking_id(booking_id);
        this.setTotal(total);
    }


    public String getCustomer_nic() {
        return customer_nic;
    }

    public void setCustomer_nic(String customer_nic) {
        this.customer_nic = customer_nic;
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


    @Override
    public String toString() {
        return "Booking{" +
                "customer_nic='" + customer_nic + '\'' +
                ", booking_id='" + booking_id + '\'' +
                ", total=" + total +
                '}';
    }
}
