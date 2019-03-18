package entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name = Customer.GET_ALL_CUSTOMERS, query = "SELECT customer FROM Customer customer")
})
public class Customer {

    public static final String GET_ALL_CUSTOMERS = "Customer.getAllCustomers";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "last_name")
    private String lastName;
    private String email;

    public Customer() {
    }

    public Customer(String lastName, String email) {
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id &&
                lastName.equals(customer.lastName) &&
                email.equals(customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, email);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
