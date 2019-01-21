package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.RoomBookingDetailDTO;
import lk.ijse.dep.app.entity.RoomBookingDetailPK;

import java.util.List;

public interface ManageRoomBookingDetailBO extends SuperBO {
    boolean saveRoomBookingDetail(RoomBookingDetailDTO roomBookingDetailDTO)throws Exception;
    boolean updateRoomBookingDetail(RoomBookingDetailDTO roomBookingDetailDTO)throws Exception;
    boolean deleteRoomBookingDetail (RoomBookingDetailDTO roomBookingDetailDTO)throws Exception;
    RoomBookingDetailDTO findRoomBookingDetail(RoomBookingDetailPK roomBookingDetailPK)throws Exception;
    List<RoomBookingDetailDTO> getRoomBookingDetails ()throws Exception;
}
