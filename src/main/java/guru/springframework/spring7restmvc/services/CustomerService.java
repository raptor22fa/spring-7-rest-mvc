package guru.springframework.spring7restmvc.services;

import java.util.List;
import java.util.UUID;
import guru.springframework.spring7restmvc.model.Customer;

/**
 *
 * @author Raptor
 */
public interface CustomerService {

    List<Customer> listCustomers();

    Customer getCustomerById(UUID id);
}
