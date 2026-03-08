package guru.springframework.spring7restmvc.controller;

import guru.springframework.spring7restmvc.entities.Customer;
import guru.springframework.spring7restmvc.mappers.CustomerMapper;
import guru.springframework.spring7restmvc.model.CustomerDTO;
import guru.springframework.spring7restmvc.repositories.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class CustomerControllerIT {

    @Autowired
    CustomerController customerController;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Test
    void testListAllCustomers() {
        // Given
        // When
        final List<CustomerDTO> customerDtos = customerController.listAllCustomers();

        // Then
        assertThat(customerDtos.size()).isEqualTo(3);
    }

    @Rollback
    @Transactional
    @Test
    void testListAllCustomersIsEmpty() {
        // Given
        // When
        customerRepository.deleteAll();
        final List<CustomerDTO> customerDtos = customerController.listAllCustomers();

        // Then
        assertThat(customerDtos).isEmpty();
    }

    @Test
    void testGetCustomerById() {
        // Given
        final Customer customer = customerRepository.findAll().getFirst();

        // When
        final CustomerDTO customerDto = customerController.getCustomerById(customer.getId());

        // Then
        assertThat(customerDto).isNotNull();
        assertThat(customerDto.getId()).isEqualTo(customer.getId());
    }

    @Test
    void testGetCustomerByIdNotFound() {
        // Given
        // When
        // Then
        assertThrows(NotFoundException.class, () -> {
            customerController.getCustomerById(UUID.randomUUID());
        });
    }

    @Rollback
    @Transactional
    @Test
    void testCreateCustomer() {
        // Given
        CustomerDTO customerDto = CustomerDTO.builder()
                .name("New Customer")
                .build();

        // When
        final ResponseEntity responseEntity = customerController.handlePost(customerDto);

        // Then
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation()).isNotNull();

        String[] locationUUID = responseEntity.getHeaders().getLocation().getPath().split("/");
        UUID savedUUID = UUID.fromString(locationUUID[4]);

        Customer savedCustomer = customerRepository.findById(savedUUID).orElse(null);
        assertThat(savedCustomer).isNotNull();
    }

    @Rollback
    @Transactional
    @Test
    void testUpdateCustomerById() {
        // Given
        Customer customer = customerRepository.findAll().getFirst();
        CustomerDTO customerDto = customerMapper.customerToCustomerDto(customer);
        customerDto.setName("Updated Name");

        // When
        final ResponseEntity responseEntity = customerController.updateCustomerByID(customer.getId(), customerDto);

        // Then
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(204);

        Customer updatedCustomer = customerRepository.findById(customer.getId()).orElse(null);
        assertThat(updatedCustomer).isNotNull();
        assertThat(updatedCustomer.getName()).isEqualTo("Updated Name");
    }

    @Test
    void testUpdateCustomerByIdNotFound() {
        // Given
        CustomerDTO customerDto = CustomerDTO.builder().name("Updated Name").build();

        // When
        // Then
        assertThrows(NotFoundException.class, () -> {
            customerController.updateCustomerByID(UUID.randomUUID(), customerDto);
        });
    }

    @Rollback
    @Transactional
    @Test
    void testDeleteCustomerById() {
        // Given
        Customer customer = customerRepository.findAll().getFirst();

        // When
        final ResponseEntity responseEntity = customerController.deleteCustomerById(customer.getId());

        // Then
        assertThat(responseEntity.getStatusCode().value()).isEqualTo(204);
        assertThat(customerRepository.findById(customer.getId()).isEmpty());
    }

    @Test
    void testDeleteCustomerByIdNotFound() {
        // Given
        // When
        // Then
        assertThrows(NotFoundException.class, () -> {
            customerController.deleteCustomerById(UUID.randomUUID());
        });
    }
}
