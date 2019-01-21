package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.Crudutil;
import lk.ijse.dep.app.dao.custom.MealTypeDAO;
import lk.ijse.dep.app.entity.MealType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MealTypeDAOImpl implements MealTypeDAO {
    @Override
    public boolean save(MealType mealType) throws Exception {
        return Crudutil.<Integer>execute("INSERT INTO MealType VALUES(?,?,?)",
               mealType.getMeal_type_id(),mealType.getMeal_type_name(),mealType.getPrice())>0;
    }

    @Override
    public boolean update(MealType mealType) throws Exception {
        return Crudutil.<Integer>execute("UPDATE MealType SET meal_type_id=?,meal_type_name=?,price=? WHERE meal_type_id=?",
                mealType.getMeal_type_id(),mealType.getMeal_type_name(),mealType.getPrice(),mealType.getMeal_type_id())>0;
    }

    @Override
    public boolean delete(MealType mealType) throws Exception {
        return Crudutil.<Integer>execute("DELETE FROM MealType WHERE meal_type_id=?", mealType.getMeal_type_id())>0;
    }

    @Override
    public Optional<MealType> find(String meal_type_id) throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM MealType WHERE meal_type_id=?", meal_type_id);
        MealType mealType=null;
        while (rst.next()){
            mealType= new MealType(rst.getString(1),rst.getString(2),rst.getDouble(3));

        }
        return Optional.ofNullable(mealType);
    }

    @Override
    public Optional<List<MealType>> findAll() throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM MealType ");
        ArrayList<MealType> mealTypes=new ArrayList<>();
        while (rst.next()){
            mealTypes.add(new MealType(rst.getString(1),rst.getString(2),rst.getDouble(3)));
        }
        return Optional.ofNullable(mealTypes);
    }




}
