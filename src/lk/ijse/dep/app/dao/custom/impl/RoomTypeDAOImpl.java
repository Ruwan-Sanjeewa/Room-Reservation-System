package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.Crudutil;
import lk.ijse.dep.app.dao.custom.RoomTypeDAO;

import lk.ijse.dep.app.entity.RoomType;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomTypeDAOImpl implements RoomTypeDAO {
    @Override
    public boolean save(RoomType roomType) throws Exception {
        return Crudutil.<Integer>execute("INSERT INTO RoomType VALUES(?,?,?)",
                roomType.getRoom_type_name(),roomType.getDescription(),roomType.getPrice())>0;
    }

    @Override
    public boolean update(RoomType roomType) throws Exception {
        return Crudutil.<Integer>execute("UPDATE RoomType SET room_type_name=?,description=?, price=?" +
                        " WHERE room_type_name=?",
                roomType.getRoom_type_name(),roomType.getDescription(),roomType.getPrice(),roomType.getRoom_type_name())>0;
    }

    @Override
    public boolean delete(RoomType roomType) throws Exception {
        return Crudutil.<Integer>execute("DELETE FROM RoomType WHERE room_type_name=?",  roomType.getRoom_type_name())>0;
    }

    @Override
    public Optional<RoomType> find(String room_type_name) throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM RoomType WHERE room_type_name=?", room_type_name);
        RoomType roomType=null;
        while (rst.next()){
            roomType= new RoomType(rst.getString(1),rst.getString(2),rst.getDouble(3));

        }
        return Optional.ofNullable(roomType);
    }



    @Override
    public Optional<List<RoomType>> findAll() throws Exception {
        ResultSet rst= Crudutil.execute("SELECT  * FROM RoomType");
        ArrayList<RoomType> roomTypes=new ArrayList<>();
        while (rst.next()){
            roomTypes.add(new RoomType(rst.getString(1),rst.getString(2),rst.getDouble(3)));
        }
        return Optional.ofNullable(roomTypes);
    }
}
