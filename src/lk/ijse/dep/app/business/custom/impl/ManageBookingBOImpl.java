package lk.ijse.dep.app.business.custom.impl;


import com.mysql.cj.x.protobuf.MysqlxCrud;
import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageBookingBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.BookingDAO;
import lk.ijse.dep.app.dao.custom.CustomerDAO;
import lk.ijse.dep.app.dao.custom.QueryDAO;
import lk.ijse.dep.app.dao.custom.RoomBookingDetailDAO;
import lk.ijse.dep.app.db.DBConnection;
import lk.ijse.dep.app.dto.*;
import lk.ijse.dep.app.entity.Booking;
import lk.ijse.dep.app.entity.CustomEntity;
import lk.ijse.dep.app.entity.Customer;
import lk.ijse.dep.app.entity.RoomBookingDetail;

import java.util.ArrayList;
import java.util.List;

public class ManageBookingBOImpl implements ManageBookingBO {
    private BookingDAO bookingDAO;
    private CustomerDAO customerDAO;
    private RoomBookingDetailDAO roomBookingDetailDAO;
    private QueryDAO queryDAO;
    public ManageBookingBOImpl(){
        
        bookingDAO= DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.BOOKING_DAO);
        customerDAO=DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOMER_DAO);
        roomBookingDetailDAO=DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.ROOM_BOOKING_DETAIL_DAO);
        queryDAO=DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.QUERY_DAO);
    }

    @Override
    public void createBooking(BookingDTO2 bookingDTO2) throws Exception {
        DBConnection.getInstance().getConnection().setAutoCommit(false);

        boolean result = customerDAO.save(new Customer(bookingDTO2.getCustomer_name(), bookingDTO2.getCustomer_nic(), bookingDTO2.getTelephone(),
                bookingDTO2.getAddress()));
        if(!result){
            return;
        }

         result = bookingDAO.save(new Booking(bookingDTO2.getCustomer_nic(), bookingDTO2.getBooking_id(), bookingDTO2.getTotal()));

        if(!result){
            DBConnection.getInstance().getConnection().rollback();
            return;
        }


        for (RoomBookingDetailDTO roomBookingDetailDTO : bookingDTO2.getRoomBookingDetailDTOS()) {
             result = roomBookingDetailDAO.save(new RoomBookingDetail(roomBookingDetailDTO.getBooking_id(), roomBookingDetailDTO.getRoom_no(),
                    roomBookingDetailDTO.getMeal_type_id(), roomBookingDetailDTO.getCheck_in_date(), roomBookingDetailDTO.getCheck_out_date(),
                    roomBookingDetailDTO.getDuration()));

            if(!result){
                DBConnection.getInstance().getConnection().rollback();
                return;
            }

        }




        DBConnection.getInstance().getConnection().commit();
        DBConnection.getInstance().getConnection().setAutoCommit(true);

    }

    @Override
    public boolean updateBooking(BookingDTO bookingDTO) throws Exception {
        return bookingDAO.update(Converter.getEntity(bookingDTO));
    }

    @Override
    public boolean deleteBooking(BookingDTO bookingDTO) throws Exception {
        return bookingDAO.delete(Converter.getEntity(bookingDTO));
    }

    @Override
    public BookingDTO findBooking(String booking_id) throws Exception {
        return bookingDAO.find(booking_id).map(Converter::<BookingDTO>getDTO).orElse(null);
    }

    @Override
    public List<CustomDTO> viewBookings() throws Exception {
        List<CustomEntity> viewBookings = queryDAO.viewBookings();
        ArrayList<CustomDTO> bookings=new ArrayList<>();

        for (CustomEntity viewBooking : viewBookings) {
            bookings.add(new CustomDTO(viewBooking.getCustomer_nic(),viewBooking.getCustomer_name(),viewBooking.getBooking_id(),
                    viewBooking.getRoom_id()));
        }
        return bookings;
    }

    @Override
    public List<BookingDTO> getBookings() throws Exception {
        return bookingDAO.findAll().map(Converter::<BookingDTO>getDTOList).get();
    }

}
