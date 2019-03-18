package services;

import entities.Customer;

import java.util.List;

public interface CustomerService {

    public Customer addCustomer(Customer customer);

    public Customer getCustomerById(int id);

    public List<Customer> getCustomers();

    public void saveCustomer(Customer customer);

    public Customer deleteCustomerById(int id);

}
