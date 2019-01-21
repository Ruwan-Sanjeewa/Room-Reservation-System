package lk.ijse.dep.app.dto;

public class RoomTypeDTO extends SuperDTO{
    private String room_type_name;
    private String description;
    private Double price;

    public RoomTypeDTO() {
    }

    public RoomTypeDTO(String room_type_name, String description, Double price) {
        this.setRoom_type_name(room_type_name);
        this.setDescription(description);
        this.setPrice(price);
    }


    public String getRoom_type_name() {
        return room_type_name;
    }

    public void setRoom_type_name(String room_type_name) {
        this.room_type_name = room_type_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return room_type_name;
    }
}
