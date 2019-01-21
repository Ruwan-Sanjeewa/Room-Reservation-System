package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.RoomDTO;

import java.util.List;

public interface ManageRoomBO extends SuperBO {
    boolean saveRoom(RoomDTO roomDTO)throws Exception;
    boolean updateRoom(RoomDTO roomDTO)throws Exception;
    boolean deleteRoom (RoomDTO roomDTO)throws Exception;
    RoomDTO findRoom (String room_no)throws Exception;
    List<RoomDTO> getRooms ()throws Exception;
}
