package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.MealTypeDTO;

import java.util.List;

public interface ManageMealTypeBO extends SuperBO {
    boolean saveMealType (MealTypeDTO mealTypeDTO)throws Exception;
    boolean updateMealType (MealTypeDTO mealTypeDTO)throws Exception;
    boolean deleteMealType (MealTypeDTO mealTypeDTO)throws Exception;
    MealTypeDTO findMealType (String meal_type_id)throws Exception;
    List<MealTypeDTO> getMealTypes ()throws Exception;
}
