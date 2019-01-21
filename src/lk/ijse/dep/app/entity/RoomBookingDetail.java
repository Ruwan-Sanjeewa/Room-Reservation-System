package lk.ijse.dep.app.entity;

import java.time.LocalDate;

public class RoomBookingDetail extends SuperEntity {
    private RoomBookingDetailPK roomBookingDetailPK;
    private String meal_type_id;
    private LocalDate check_in_date;
    private LocalDate check_out_date;
    private int duration;

    public RoomBookingDetail() {
    }

    public RoomBookingDetail(RoomBookingDetailPK roomBookingDetailPK, String meal_type_id, LocalDate check_in_date, LocalDate check_out_date, int duration) {
        this.setRoomBookingDetailPK(roomBookingDetailPK);
        this.setMeal_type_id(meal_type_id);
        this.setCheck_in_date(check_in_date);
        this.setCheck_out_date(check_out_date);
        this.setDuration(duration);
    }

    public RoomBookingDetail(String booking_id,String room_no,String meal_type_id, LocalDate check_in_date, LocalDate check_out_date, int duration) {
        this.setRoomBookingDetailPK(new RoomBookingDetailPK(booking_id,room_no));
        this.setMeal_type_id(meal_type_id);
        this.setCheck_in_date(check_in_date);
        this.setCheck_out_date(check_out_date);
        this.setDuration(duration);
    }


    public RoomBookingDetailPK getRoomBookingDetailPK() {
        return roomBookingDetailPK;
    }

    public void setRoomBookingDetailPK(RoomBookingDetailPK roomBookingDetailPK) {
        this.roomBookingDetailPK = roomBookingDetailPK;
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
        return "RoomBookingDetail{" +
                "roomBookingDetailPK=" + roomBookingDetailPK +
                ", meal_type_id='" + meal_type_id + '\'' +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", duration=" + duration +
                '}';
    }
}
