package services.impl;

import entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import repositories.CustomerRepository;
import services.CustomerService;

import java.util.List;

@Component
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer addCustomer(Customer customer) {
        if (customer == null)
            return null;
        return customerRepository.addCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerRepository.getCustomerById(id);
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    @Override
    public void saveCustomer(Customer customer) {
        if (customer == null)
            return;
        customerRepository.saveCustomer(customer);
    }

    @Override
    public Customer deleteCustomerById(int id) {
        return customerRepository.deleteCustomerById(id);
    }
}
