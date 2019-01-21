package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.PaymentDTO;

import java.util.List;

public interface ManagePaymentBO extends SuperBO {
    boolean savePayment(PaymentDTO paymentDTO)throws Exception;
    boolean updatePayment(PaymentDTO paymentDTO)throws Exception;
    boolean deletePayment (PaymentDTO paymentDTO)throws Exception;
    PaymentDTO findPayment (String payment_id)throws Exception;
    List<PaymentDTO> getPayments ()throws Exception;
}
