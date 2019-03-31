package controllers;

import entities.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import services.CustomerService;

import java.util.List;

@RestController
@RequestMapping(CustomerController.BASE_URL)
public class CustomerController {

    public final static String BASE_URL = "/api/v1/customers";

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createCustomer(@RequestBody Customer customer) {
        customer = customerService.addCustomer(customer);
        return "/api/v1/customers/" + customer.getId();
    }

    @GetMapping
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return customerService.getCustomerById(id);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer newCustomer) {
        System.out.println(newCustomer.getLastName());
        System.out.println(newCustomer.getEmail());
        Customer customer = customerService.getCustomerById(id);
        customer.setLastName(newCustomer.getLastName());
        customer.setEmail(newCustomer.getEmail());
        customerService.saveCustomer(customer);
        return customer;
    }

    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable int id) {
        return customerService.deleteCustomerById(id);
    }

}
