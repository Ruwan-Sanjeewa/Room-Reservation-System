package lk.ijse.dep.app.dao.custom.impl;

import lk.ijse.dep.app.dao.Crudutil;
import lk.ijse.dep.app.dao.custom.CustomerDAO;

import lk.ijse.dep.app.entity.Customer;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean save(Customer customer) throws Exception {
        return Crudutil.<Integer>execute("INSERT INTO Customer VALUES(?,?,?,?)",
               customer.getCustomer_name(),customer.getCustomer_nic(),customer.getTelephone(),customer.getAddress())>0;
    }

    @Override
    public boolean update(Customer customer) throws Exception {
        return Crudutil.<Integer>execute("UPDATE Customer SET customer_name=?,customer_nic=?,telephone=?,address=? WHERE customer_nic=?",
                customer.getCustomer_name(),customer.getCustomer_nic(),customer.getTelephone(),customer.getAddress())>0;
    }

    @Override
    public boolean delete(Customer customer) throws Exception {
        return Crudutil.<Integer>execute("DELETE FROM Customer WHERE customer_nic=?",customer.getCustomer_nic())>0;
    }

    @Override
    public Optional<Customer> find(String customer_nic) throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM Customer WHERE customer_nic=?", customer_nic);
        Customer customer=null;
        while (rst.next()){
            customer= new Customer(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4));

        }
        return Optional.ofNullable(customer);
    }

    @Override
    public Optional<List<Customer>> findAll() throws Exception {
        ResultSet rst= Crudutil.execute("SELECT * FROM Customer ");
        ArrayList<Customer>customers=new ArrayList<>();
        while (rst.next()){
            customers.add(new Customer(rst.getString(1),rst.getString(2),rst.getString(3),
                    rst.getString(4)));
        }
        return Optional.ofNullable(customers);
    }




}


