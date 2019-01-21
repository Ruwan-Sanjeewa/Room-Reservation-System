package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.Crudutil;
import lk.ijse.dep.app.dao.custom.RoomDAO;
import lk.ijse.dep.app.entity.Room;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public boolean save(Room room) throws Exception {
        return Crudutil.<Integer>execute("INSERT INTO Room VALUES(?,?)",
               room.getRoom_no(),room.getRoom_type())>0;
    }

    @Override
    public boolean update(Room room) throws Exception {
        return Crudutil.<Integer>execute("UPDATE Room SET room_no=?,room_type=? WHERE room_no=?",
                room.getRoom_no(),room.getRoom_type(),room.getRoom_no())>0;
    }

    @Override
    public boolean delete(Room room) throws Exception {
        return Crudutil.<Integer>execute("DELETE FROM Room WHERE room_no=?",  room.getRoom_no())>0;
    }

    @Override
    public Optional<Room> find(String room_no) throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM Room WHERE room_no=?", room_no);
        Room room=null;
        while (rst.next()){
            room= new Room(rst.getString(1),rst.getString(2));

        }
        return Optional.ofNullable(room);
    }

    @Override
    public Optional<List<Room>> findAll() throws Exception {
        ResultSet rst= Crudutil.execute("SELECT DISTINCT * FROM Room ");
        ArrayList<Room> rooms=new ArrayList<>();
        while (rst.next()){
            rooms.add(new Room(rst.getString(1),rst.getString(2)));
        }
        return Optional.ofNullable(rooms);
    }



}
