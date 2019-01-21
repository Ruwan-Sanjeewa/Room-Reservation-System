package lk.ijse.dep.app.dto;


public class PaymentDTO extends SuperDTO {

    private String booking_id;
    private String payment_id;
    private Double total_price;
    private Double advance;
    private Double balance;
    private String status;


    public PaymentDTO() {
    }

    public PaymentDTO(String booking_id, String payment_id, Double total_price, Double advance, Double balance, String status) {
        this.setBooking_id(booking_id);
        this.setPayment_id(payment_id);
        this.setTotal_price(total_price);
        this.setAdvance(advance);
        this.setBalance(balance);
        this.setStatus(status);
    }


    public String getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(String booking_id) {
        this.booking_id = booking_id;
    }

    public String getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(String payment_id) {
        this.payment_id = payment_id;
    }

    public Double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(Double total_price) {
        this.total_price = total_price;
    }

    public Double getAdvance() {
        return advance;
    }

    public void setAdvance(Double advance) {
        this.advance = advance;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "PaymentDTO{" +
                "booking_id='" + booking_id + '\'' +
                ", payment_id='" + payment_id + '\'' +
                ", total_price=" + total_price +
                ", advance=" + advance +
                ", balance=" + balance +
                ", status='" + status + '\'' +
                '}';
    }
}
