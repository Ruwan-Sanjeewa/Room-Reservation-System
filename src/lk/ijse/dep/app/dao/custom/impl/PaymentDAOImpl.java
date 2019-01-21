package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.Crudutil;
import lk.ijse.dep.app.dao.custom.PaymentDAO;
import lk.ijse.dep.app.entity.Payment;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public boolean save(Payment payment) throws Exception {
        return Crudutil.<Integer>execute("INSERT INTO Payment VALUES(?,?,?,?,?,?)",
                payment.getBooking_id(),payment.getPayment_id(),payment.getTotal_price(),payment.getAdvance(),payment.getBalance(),
                payment.getStatus())>0;
    }

    @Override
    public boolean update(Payment payment) throws Exception {
        return Crudutil.<Integer>execute("UPDATE Payment SET booking_id=?,payment_id=?,total_price=?,advance=?, balance=? ,status=? WHERE payment_id=?",
                payment.getBooking_id(),payment.getPayment_id(),payment.getTotal_price(),payment.getAdvance(),payment.getBalance(),
                payment.getStatus(),payment.getPayment_id())>0;
    }

    @Override
    public boolean delete(Payment payment) throws Exception {
        return Crudutil.<Integer>execute("DELETE FROM Payment WHERE payment_id=?",  payment.getPayment_id())>0;
    }

    @Override
    public Optional<Payment> find(String payment_id) throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM Payment WHERE payment_id=?", payment_id);
        Payment payment=null;
        while (rst.next()){
            payment= new Payment(rst.getString(1),rst.getString(2),rst.getDouble(3),
                    rst.getDouble(4),rst.getDouble(5),rst.getString(6));

        }
        return Optional.ofNullable(payment);
    }

    @Override
    public Optional<List<Payment>> findAll() throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM Payment ");
        ArrayList<Payment> payments=new ArrayList<>();
        while (rst.next()){
            payments.add(new Payment(rst.getString(1),rst.getString(2),rst.getDouble(3),
                    rst.getDouble(4),rst.getDouble(5),rst.getString(6)));
        }
        return Optional.ofNullable(payments);
    }



}
