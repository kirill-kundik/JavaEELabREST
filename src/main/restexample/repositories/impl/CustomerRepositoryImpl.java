package repositories.impl;

import entities.Customer;
import org.springframework.stereotype.Repository;
import repositories.CustomerRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Customer addCustomer(final Customer customer) {
        entityManager.persist(customer);
        return customer;
    }

    @Override
    public Customer getCustomerById(int id) {
        return entityManager.find(Customer.class, id);
    }

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = null;
        try {
            Query query = entityManager.createNamedQuery(Customer.GET_ALL_CUSTOMERS, Customer.class);
            customers = query.getResultList();
        } catch (Exception ex) {
            System.err.println("Error in customer repo, get all: " + ex.toString());
        }
        return customers;
    }

    @Override
    public void saveCustomer(final Customer customer) {
        entityManager.merge(customer);
    }

    @Override
    public Customer deleteCustomerById(int id) {
        Customer customer = entityManager.find(Customer.class, id);
        entityManager.remove(customer);
        return customer;
    }
}
