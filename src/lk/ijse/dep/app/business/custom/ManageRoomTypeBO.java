package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.RoomTypeDTO;

import java.util.List;

public interface ManageRoomTypeBO extends SuperBO {
    boolean saveRoomType (RoomTypeDTO roomTypeDTO)throws Exception;
    boolean updateRoomType (RoomTypeDTO roomTypeDTO)throws Exception;
    boolean deleteRoomType (RoomTypeDTO roomTypeDTO)throws Exception;
    RoomTypeDTO findRoomType (String room_type_name)throws Exception;
    List<RoomTypeDTO> getRoomTypes ()throws Exception;
}
