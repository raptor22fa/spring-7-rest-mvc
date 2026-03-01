package guru.springframework.spring7restmvc.services;

import guru.springframework.spring7restmvc.model.Customer;

import java.util.List;
import java.util.UUID;

/**
 * Created by jt, Spring Framework Guru.
 */
public interface CustomerService {

    Customer updateCustomerById(UUID customerId, Customer customer);

    void deleteCustomerById(UUID customerId);

    Customer patchCustomerById(UUID customerId, Customer customer);

    Customer getCustomerById(UUID uuid);

    List<Customer> getAllCustomers();

    Customer saveNewCustomer(Customer customer);
}
