package lk.ijse.dep.app.dto;


import java.time.LocalDate;

public class RoomBookingDetailDTO extends SuperDTO {
    private String booking_id;
    private String room_no;
    private String meal_type_id;
    private LocalDate check_in_date;
    private LocalDate check_out_date;
    private int duration;


    public RoomBookingDetailDTO() {
    }

    public RoomBookingDetailDTO(String booking_id, String room_no, String meal_type_id, LocalDate check_in_date, LocalDate check_out_date, int duration) {
        this.setBooking_id(booking_id);
        this.setRoom_no(room_no);
        this.setMeal_type_id(meal_type_id);
        this.setCheck_in_date(check_in_date);
        this.setCheck_out_date(check_out_date);
        this.setDuration(duration);
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

    public String getMeal_type_id() {
        return meal_type_id;
    }

    public void setMeal_type_id(String meal_type_id) {
        this.meal_type_id = meal_type_id;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }


    @Override
    public String toString() {
        return "RoomBookingDetailDTO{" +
                "booking_id='" + booking_id + '\'' +
                ", room_no='" + room_no + '\'' +
                ", meal_type_id='" + meal_type_id + '\'' +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", duration=" + duration +
                '}';
    }
}
