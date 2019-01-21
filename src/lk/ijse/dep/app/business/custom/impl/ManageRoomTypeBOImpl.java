package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageRoomTypeBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.RoomTypeDAO;
import lk.ijse.dep.app.dto.RoomTypeDTO;

import java.util.List;

public class ManageRoomTypeBOImpl implements ManageRoomTypeBO {
    private RoomTypeDAO roomTypeDAO;

    public  ManageRoomTypeBOImpl(){
        roomTypeDAO= DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ROOM_TYPE_DAO);
    }

    @Override
    public boolean saveRoomType(RoomTypeDTO roomTypeDTO) throws Exception {
        return roomTypeDAO.save(Converter.getEntity(roomTypeDTO));
    }

    @Override
    public boolean updateRoomType(RoomTypeDTO roomTypeDTO) throws Exception {
        return roomTypeDAO.update(Converter.getEntity(roomTypeDTO));
    }

    @Override
    public boolean deleteRoomType(RoomTypeDTO roomTypeDTO) throws Exception {
        return roomTypeDAO.delete(Converter.getEntity(roomTypeDTO));
    }

    @Override
    public RoomTypeDTO findRoomType(String room_type_name) throws Exception {
        return roomTypeDAO.find(room_type_name).map(Converter::<RoomTypeDTO>getDTO).orElse(null);
    }

    @Override
    public List<RoomTypeDTO> getRoomTypes() throws Exception {
        return roomTypeDAO.findAll().map(Converter::<RoomTypeDTO>getDTOList).get();
    }
}
