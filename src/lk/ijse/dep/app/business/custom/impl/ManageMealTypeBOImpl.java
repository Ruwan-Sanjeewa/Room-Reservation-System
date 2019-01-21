package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageMealTypeBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.MealTypeDAO;
import lk.ijse.dep.app.dto.MealTypeDTO;

import java.util.List;

public class ManageMealTypeBOImpl implements ManageMealTypeBO {
    private MealTypeDAO mealTypeDAO;
    public ManageMealTypeBOImpl(){
        mealTypeDAO= DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.MEAL_TYPE_DAO);
    }

    @Override
    public boolean saveMealType(MealTypeDTO mealTypeDTO) throws Exception {
        return mealTypeDAO.save(Converter.getEntity(mealTypeDTO));
    }

    @Override
    public boolean updateMealType(MealTypeDTO mealTypeDTO) throws Exception {
        return mealTypeDAO.update(Converter.getEntity(mealTypeDTO));
    }

    @Override
    public boolean deleteMealType(MealTypeDTO mealTypeDTO) throws Exception {
        return mealTypeDAO.delete(Converter.getEntity(mealTypeDTO));
    }

    @Override
    public MealTypeDTO findMealType(String meal_type_id) throws Exception {
        return mealTypeDAO.find(meal_type_id).map(Converter::<MealTypeDTO>getDTO).orElse(null);
    }

    @Override
    public List<MealTypeDTO> getMealTypes() throws Exception {
        return mealTypeDAO.findAll().map(Converter::<MealTypeDTO>getDTOList).get();
    }
}
