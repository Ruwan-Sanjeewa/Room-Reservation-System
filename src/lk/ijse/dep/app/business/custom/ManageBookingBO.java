package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.BookingDTO;
import lk.ijse.dep.app.dto.BookingDTO2;
import lk.ijse.dep.app.dto.CustomDTO;
import lk.ijse.dep.app.entity.CustomEntity;

import java.util.List;

public interface ManageBookingBO extends SuperBO {
    void createBooking (BookingDTO2 bookingDTO2)throws Exception;
    boolean updateBooking (BookingDTO bookingDTO)throws Exception;
    boolean deleteBooking (BookingDTO bookingDTO)throws Exception;
    BookingDTO findBooking (String booking_id)throws Exception;
    List<CustomDTO> viewBookings ()throws Exception;
    List<BookingDTO>getBookings()throws Exception;
}
