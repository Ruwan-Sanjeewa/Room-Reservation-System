package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.Crudutil;
import lk.ijse.dep.app.dao.custom.RoomBookingDetailDAO;

import lk.ijse.dep.app.entity.RoomBookingDetail;
import lk.ijse.dep.app.entity.RoomBookingDetailPK;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoomBookingDetailDAOImpl implements RoomBookingDetailDAO {
    @Override
    public boolean save(RoomBookingDetail roomBookingDetail) throws Exception {
        return Crudutil.<Integer>execute("INSERT INTO RoomBookingDetail VALUES(?,?,?,?,?,?)",
                roomBookingDetail.getRoomBookingDetailPK().getBooking_id(),roomBookingDetail.getRoomBookingDetailPK().getRoom_no(),
                roomBookingDetail.getMeal_type_id(),roomBookingDetail.getCheck_in_date(),roomBookingDetail.getCheck_out_date(),
                roomBookingDetail.getDuration())>0;
    }

    @Override
    public boolean update(RoomBookingDetail roomBookingDetail) throws Exception {
        return Crudutil.<Integer>execute("UPDATE RoomBookingDetail SET booking_id=?,room_no=?,meal_type_id=?,check_in_date=?" +
                        "check_out_date=?,duration=? WHERE booking_id=?" +
                        "AND room_no=?",
                roomBookingDetail.getRoomBookingDetailPK().getBooking_id(),roomBookingDetail.getRoomBookingDetailPK().getRoom_no(),
                roomBookingDetail.getMeal_type_id(),roomBookingDetail.getCheck_in_date(),
                roomBookingDetail.getCheck_out_date(),roomBookingDetail.getDuration(),
                roomBookingDetail.getRoomBookingDetailPK().getBooking_id(),
                roomBookingDetail.getRoomBookingDetailPK().getRoom_no())>0;
    }

    @Override
    public boolean delete(RoomBookingDetail roomBookingDetail) throws Exception {
        return Crudutil.<Integer>execute("DELETE FROM RoomBookingDetail WHERE booking_id=? AND room_no=?",
                roomBookingDetail.getRoomBookingDetailPK().getBooking_id(),
                roomBookingDetail.getRoomBookingDetailPK().getRoom_no())>0;
    }

    @Override
    public Optional<RoomBookingDetail> find(RoomBookingDetailPK roomBookingDetailPK) throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM RoomBookingDetail WHERE booking_id=? AND room_no=?",
                roomBookingDetailPK.getBooking_id(),roomBookingDetailPK.getRoom_no());
        RoomBookingDetail roomBookingDetail=null;
        while (rst.next()){
            roomBookingDetail= new RoomBookingDetail(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getDate(4).toLocalDate(),rst.getDate(5).toLocalDate(),
                    rst.getInt(6));

        }
        return Optional.ofNullable(roomBookingDetail);
    }

    @Override
    public Optional<List<RoomBookingDetail>> findAll() throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM RoomBookingDetail ");
        ArrayList<RoomBookingDetail> roomBookingDetails=new ArrayList<>();
        while (rst.next()){
            roomBookingDetails.add(new RoomBookingDetail(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getDate(4).toLocalDate(),rst.getDate(5).toLocalDate(),
                    rst.getInt(6)));
        }
        return Optional.ofNullable(roomBookingDetails);
    }


}
