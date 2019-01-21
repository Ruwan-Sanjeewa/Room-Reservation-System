package lk.ijse.dep.app.view.util;

import lk.ijse.dep.app.entity.SuperEntity;

public class RoomTM extends SuperEntity{
    private String room_no;
    private String room_type;

    public RoomTM() {
    }

    public RoomTM(String room_no, String room_type) {
        this.setRoom_no(room_no);
        this.setRoom_type(room_type);
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

    @Override
    public String toString() {
        return "RoomTM{" +
                "room_no='" + room_no + '\'' +
                ", room_type='" + room_type + '\'' +
                '}';
    }
}
