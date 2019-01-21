package lk.ijse.dep.app.business.custom.impl;

import lk.ijse.dep.app.business.Converter;
import lk.ijse.dep.app.business.custom.ManageCustomerBO;
import lk.ijse.dep.app.dao.DAOFactory;
import lk.ijse.dep.app.dao.custom.CustomerDAO;
import lk.ijse.dep.app.dto.CustomerDTO;

import java.util.List;

public class ManageCustomerBOImpl implements ManageCustomerBO {
    private CustomerDAO customerDAO;
    public ManageCustomerBOImpl(){
        customerDAO= DAOFactory.getInstance().getDao(DAOFactory.DAOTypes.CUSTOMER_DAO);
    }
    @Override
    public boolean createCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.save(Converter.getEntity(customerDTO));
    }

    @Override
    public boolean updateCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.update(Converter.getEntity(customerDTO));
    }

    @Override
    public boolean deleteCustomer(CustomerDTO customerDTO) throws Exception {
        return customerDAO.delete(Converter.getEntity(customerDTO));
    }

    @Override
    public CustomerDTO findCustomer(String customer_nic) throws Exception {
        return customerDAO.find(customer_nic).map(Converter::<CustomerDTO>getDTO).orElse(null);
    }

    @Override
    public List<CustomerDTO> getCustomers() throws Exception {
        return customerDAO.findAll().map(Converter::<CustomerDTO>getDTOList).get();
    }
}
