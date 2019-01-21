package lk.ijse.dep.app.dao.custom;

import lk.ijse.dep.app.dao.SuperDAO;
import lk.ijse.dep.app.entity.CustomEntity;

import java.time.LocalDate;
import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<CustomEntity> getAvailableRooms(LocalDate check_in, LocalDate check_out, String room_type) throws Exception;
    List<CustomEntity>viewBookings()throws Exception;
}
