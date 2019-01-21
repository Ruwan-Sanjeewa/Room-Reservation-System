package lk.ijse.dep.app.business.custom;

import lk.ijse.dep.app.business.SuperBO;
import lk.ijse.dep.app.dto.CustomerDTO;

import java.util.List;

public interface ManageCustomerBO extends SuperBO {
    boolean createCustomer (CustomerDTO customerDTO)throws Exception;
    boolean updateCustomer (CustomerDTO customerDTO)throws Exception;
    boolean deleteCustomer (CustomerDTO customerDTO)throws Exception;
    CustomerDTO findCustomer (String customer_nic)throws Exception;
    List<CustomerDTO> getCustomers ()throws Exception;
}
