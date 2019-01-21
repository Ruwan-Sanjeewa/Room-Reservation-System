package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageRoomBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.RoomDAO;
import lk.ijse.dep.app.dto.RoomDTO;

import java.util.List;

public class ManageRoomBOImpl implements ManageRoomBO {
    private RoomDAO roomDAO;
    public ManageRoomBOImpl(){
        roomDAO= DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ROOM_DAO);
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.save(Converter.getEntity(roomDTO));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.update(Converter.getEntity(roomDTO));
    }

    @Override
    public boolean deleteRoom(RoomDTO roomDTO) throws Exception {
        return roomDAO.delete(Converter.getEntity(roomDTO));
    }

    @Override
    public RoomDTO findRoom(String room_no) throws Exception {
        return roomDAO.find(room_no).map(Converter::<RoomDTO>getDTO).orElse(null);
    }

    @Override
    public List<RoomDTO> getRooms() throws Exception {
        return roomDAO.findAll().map(Converter::<RoomDTO>getDTOList).get();
    }
}
