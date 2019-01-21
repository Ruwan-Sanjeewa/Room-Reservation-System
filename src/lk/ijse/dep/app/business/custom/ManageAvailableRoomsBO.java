package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.CustomDTO;

import java.time.LocalDate;
import java.util.List;

public interface ManageAvailableRoomsBO extends SuperBO {
    List<CustomDTO> getAvailableRooms(LocalDate check_in, LocalDate check_out, String room_type) throws Exception;
}
