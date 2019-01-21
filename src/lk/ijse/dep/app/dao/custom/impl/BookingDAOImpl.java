package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.Crudutil;
import lk.ijse.dep.app.dao.custom.BookingDAO;
import lk.ijse.dep.app.entity.Booking;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDAOImpl implements BookingDAO {
    @Override
    public boolean save(Booking booking) throws Exception {
        return Crudutil.<Integer>execute("INSERT INTO Booking VALUES(?,?,?)",
                booking.getCustomer_nic(),booking.getBooking_id(),booking.getTotal())>0;
    }

    @Override
    public boolean update(Booking booking) throws Exception {
        return Crudutil.<Integer>execute("UPDATE Booking SET customer_nic=?,booking_id=?," +
                "total=? WHERE booking_id=?",booking.getCustomer_nic(),
                booking.getBooking_id(),booking.getTotal(),booking.getBooking_id())>0;
    }

    @Override
    public boolean delete(Booking booking) throws Exception {
        return Crudutil.<Integer>execute("DELETE FROM Booking WHERE booking_id=?",booking.getBooking_id())>0;
    }

    @Override
    public Optional<Booking> find(String booking_id) throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM Booking WHERE booking_id=?", booking_id);
        Booking booking=null;
        while (rst.next()){
            booking= new Booking(rst.getString(1),rst.getString(2),rst.getDouble(3));

        }
        return Optional.ofNullable(booking);
    }

    @Override
    public Optional<List<Booking>> findAll() throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM Booking ");
        ArrayList<Booking>bookings=new ArrayList<>();
        while (rst.next()){
            bookings.add(new Booking(rst.getString(1),rst.getString(2),rst.getDouble(3)));
        }
        return Optional.ofNullable(bookings);
    }


}
