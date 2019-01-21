package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.Crudutil;
import lk.ijse.dep.app.dao.custom.QueryDAO;
import lk.ijse.dep.app.entity.CustomEntity;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> getAvailableRooms(LocalDate check_in,LocalDate check_out,String room_type) throws Exception {
        ResultSet rst= Crudutil.execute("SELECT  DISTINCT r.room_no,r.room_type FROM RoomBookingDetail ro INNER JOIN Room  r on ro.room_no != r.room_no WHERE r.room_no\n" +
                " IN(ro.check_in_date NOT BETWEEN ? AND ? AND ro.check_out_date NOT BETWEEN ? AND ?  )\n" +
                "and r.room_type=? ", check_in,check_out,check_in,check_out,room_type);
        ArrayList<CustomEntity>availableRooms=new ArrayList<>();
        while(rst.next()){
            String roomNo = rst.getString(1);
            String roomType = rst.getString(2);
            availableRooms.add(new CustomEntity(roomNo,roomType));

        }
        return availableRooms;
    }

    @Override
    public List<CustomEntity> viewBookings() throws Exception {
        ResultSet rst= Crudutil.execute("SELECT c.customer_nic,c.customer_name,b.booking_id,ro.room_no FROM Customer c INNER JOIN Booking B on c.customer_nic = B.customer_nic\n" +
                "INNER JOIN RoomBookingDetail ro on B.booking_id = ro.booking_id");
        ArrayList<CustomEntity>viewBookings=new ArrayList<>();

        while(rst.next()){
            String customerNic = rst.getString(1);
            String customerName = rst.getString(2);
            String bookingId = rst.getString(3);
            String roomNo = rst.getString(4);

            viewBookings.add(new CustomEntity(customerNic,customerName,bookingId,roomNo));
        }
        return viewBookings;
    }


}
