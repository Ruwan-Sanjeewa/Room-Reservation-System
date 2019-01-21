package lk.ijse.dep.app.dto;


public class MealTypeDTO extends SuperDTO {
    private String meal_type_id;
    private String meal_type_name;
    private Double price;

    public MealTypeDTO() {
    }

    public MealTypeDTO(String meal_type_id, String meal_type_name, Double price) {
        this.setMeal_type_id(meal_type_id);
        this.setMeal_type_name(meal_type_name);
        this.setPrice(price);
    }


    public String getMeal_type_id() {
        return meal_type_id;
    }

    public void setMeal_type_id(String meal_type_id) {
        this.meal_type_id = meal_type_id;
    }

    public String getMeal_type_name() {
        return meal_type_name;
    }

    public void setMeal_type_name(String meal_type_name) {
        this.meal_type_name = meal_type_name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return meal_type_name ;

    }
}
