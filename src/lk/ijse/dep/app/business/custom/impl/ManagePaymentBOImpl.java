package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManagePaymentBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.PaymentDAO;
import lk.ijse.dep.app.dto.PaymentDTO;

import java.util.List;

public class ManagePaymentBOImpl implements ManagePaymentBO {
    private PaymentDAO paymentDAO;
    public ManagePaymentBOImpl(){
        paymentDAO= DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.PAYMENT_DAO);
    }

    @Override
    public boolean savePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.save(Converter.getEntity(paymentDTO));
    }

    @Override
    public boolean updatePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.update(Converter.getEntity(paymentDTO));
    }

    @Override
    public boolean deletePayment(PaymentDTO paymentDTO) throws Exception {
        return paymentDAO.delete(Converter.getEntity(paymentDTO));
    }

    @Override
    public PaymentDTO findPayment(String payment_id) throws Exception {
        return paymentDAO.find(payment_id).map(Converter::<PaymentDTO>getDTO).orElse(null);
    }

    @Override
    public List<PaymentDTO> getPayments() throws Exception {
        return paymentDAO.findAll().map(Converter::<PaymentDTO>getDTOList).get();
    }
}
