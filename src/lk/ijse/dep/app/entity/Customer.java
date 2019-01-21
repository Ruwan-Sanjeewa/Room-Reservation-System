package lk.ijse.dep.app.entity;

public class Customer extends SuperEntity{
    private String customer_name;
    private String customer_nic;
    private String telephone;
    private String address;

    public Customer() {
    }

    public Customer(String customer_name, String customer_nic, String telephone, String address) {
        this.setCustomer_name(customer_name);
        this.setCustomer_nic(customer_nic);
        this.setTelephone(telephone);
        this.setAddress(address);
    }


    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_nic() {
        return customer_nic;
    }

    public void setCustomer_nic(String customer_nic) {
        this.customer_nic = customer_nic;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return "CustomerDTO{" +
                "customer_name='" + customer_name + '\'' +
                ", customer_nic='" +  + '\'' +
                ", telephone='" + telephone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
