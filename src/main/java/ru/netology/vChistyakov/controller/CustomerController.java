package ru.netology.vChistyakov.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.vChistyakov.domain.Customer;
import ru.netology.vChistyakov.domain.dto.CustomerDTO;
import ru.netology.vChistyakov.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public CustomerDTO postCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);

        return new CustomerDTO(customer.getId(), customer.getName());
    }

    @GetMapping
    public Iterable<CustomerDTO> getCustomers() {
        List<CustomerDTO> customersDTO = new ArrayList<>();

        for (Customer customer : customerService.getCustomers()) {
            customersDTO.add(new CustomerDTO(customer.getId(), customer.getName()));
        }

        return customersDTO;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Integer id) {
        for(Customer customer : customerService.getCustomers()){
            if(customer.getId().equals(id)){
                return new ResponseEntity<>(new CustomerDTO(customer.getId(), customer.getName()), HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
