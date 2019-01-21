package lk.ijse.dep.app.business.custom.impl;


import lk.ijse.dep.app.business.custom.ManageAvailableRoomsBO;
import lk.ijse.dep.app.dao.DAOFactory;

import lk.ijse.dep.app.dao.custom.QueryDAO;
import lk.ijse.dep.app.dto.CustomDTO;

import lk.ijse.dep.app.entity.CustomEntity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ManageAvailableRoomsBOImpl implements ManageAvailableRoomsBO {

    private QueryDAO queryDAO;
    public ManageAvailableRoomsBOImpl(){
        queryDAO= DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.QUERY_DAO);
    }

    @Override
    public List<CustomDTO> getAvailableRooms(LocalDate check_in, LocalDate check_out, String room_type) throws Exception {
        List<CustomEntity> availableRooms = queryDAO.getAvailableRooms(check_in, check_out, room_type);
        ArrayList<CustomDTO> AvailableRoom = new ArrayList<>();
        for (CustomEntity availableRoom : availableRooms) {
            AvailableRoom.add(new CustomDTO(availableRoom.getRoom_no(),availableRoom.getRoom_type()));
        }
        return AvailableRoom;
    }
}
