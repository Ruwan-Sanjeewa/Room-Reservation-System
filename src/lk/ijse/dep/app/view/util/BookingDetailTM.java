package lk.ijse.dep.app.view.util;

import java.time.LocalDate;

public class BookingDetailTM {
    private String roomNo;
    private String roomType;
    private String mealTypeId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int duration;
    private Double totalPerDay;
    private Double total;

    public BookingDetailTM() {
    }

    public BookingDetailTM(String roomNo, String roomType, String mealTypeId, LocalDate checkIn, LocalDate checkOut, int duration, Double totalPerDay, Double total) {
        this.setRoomNo(roomNo);
        this.setRoomType(roomType);
        this.setMealTypeId(mealTypeId);
        this.setCheckIn(checkIn);
        this.setCheckOut(checkOut);
        this.setDuration(duration);
        this.setTotalPerDay(totalPerDay);
        this.setTotal(total);
    }


    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public String getMealTypeId() {
        return mealTypeId;
    }

    public void setMealTypeId(String mealTypeId) {
        this.mealTypeId = mealTypeId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Double getTotalPerDay() {
        return totalPerDay;
    }

    public void setTotalPerDay(Double totalPerDay) {
        this.totalPerDay = totalPerDay;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "BookingDetailTM{" +
                "roomNo='" + roomNo + '\'' +
                ", roomType='" + roomType + '\'' +
                ", mealTypeId='" + mealTypeId + '\'' +
                ", checkIn=" + checkIn +
                ", checkOut=" + checkOut +
                ", duration=" + duration +
                ", totalPerDay=" + totalPerDay +
                ", total=" + total +
                '}';
    }
}
