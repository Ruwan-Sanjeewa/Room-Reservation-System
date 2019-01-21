package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageRoomBookingDetailBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.RoomBookingDetailDAO;
import lk.ijse.dep.app.dto.RoomBookingDetailDTO;
import lk.ijse.dep.app.entity.RoomBookingDetailPK;

import java.util.List;

public class ManageRoomBookingDetailBOImpl implements ManageRoomBookingDetailBO {
    private RoomBookingDetailDAO roomBookingDetailDAO;

    public ManageRoomBookingDetailBOImpl(){
        roomBookingDetailDAO= DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ROOM_BOOKING_DETAIL_DAO);
    }

    @Override
    public boolean saveRoomBookingDetail(RoomBookingDetailDTO roomBookingDetailDTO) throws Exception {
        return roomBookingDetailDAO.save(Converter.getEntity(roomBookingDetailDTO));
    }

    @Override
    public boolean updateRoomBookingDetail(RoomBookingDetailDTO roomBookingDetailDTO) throws Exception {
        return roomBookingDetailDAO.update(Converter.getEntity(roomBookingDetailDTO));
    }

    @Override
    public boolean deleteRoomBookingDetail(RoomBookingDetailDTO roomBookingDetailDTO) throws Exception {
        return roomBookingDetailDAO.delete(Converter.getEntity(roomBookingDetailDTO));
    }

    @Override
    public RoomBookingDetailDTO findRoomBookingDetail(RoomBookingDetailPK roomBookingDetailPK) throws Exception {
        return roomBookingDetailDAO.find(roomBookingDetailPK).map(Converter::<RoomBookingDetailDTO>getDTO).orElse(null);
    }

    @Override
    public List<RoomBookingDetailDTO> getRoomBookingDetails() throws Exception {
        return roomBookingDetailDAO.findAll().map(Converter::<RoomBookingDetailDTO>getDTOList).get();
    }
}
