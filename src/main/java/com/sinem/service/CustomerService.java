package com.sinem.service;

import com.sinem.dto.request.CustomerLoginRequestDto;
import com.sinem.dto.response.CustomerFindAllResponseDto;
import com.sinem.exceptions.YemekSepetiException;
import com.sinem.exceptions.ErrorType;
import com.sinem.repository.ICustomerRepository;
import com.sinem.repository.entity.Customer;
import com.sinem.repository.entity.Order;
import com.sinem.utility.ActivationCodeGenerator;
import com.sinem.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService extends ServiceManager<Customer, Long> {

    private final ICustomerRepository customerRepository;

    public CustomerService(ICustomerRepository customerRepository) {
        super(customerRepository);
        this.customerRepository = customerRepository;
    }

    public Customer save(Customer customer){
        customer.setActivationCode(ActivationCodeGenerator.activationCode);
        customerRepository.save(customer);
        return customer;
    }

    public void activateAccount(Long id, int activationCode) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        if (optionalCustomer.isEmpty()){
            throw new YemekSepetiException(ErrorType.ID_NOT_FOUND);
        }
        if (optionalCustomer.get().getActivationCode() == activationCode){
            optionalCustomer.get().setActivated(true);
            update(optionalCustomer.get());
        } else {
            throw new YemekSepetiException(ErrorType.CODE_DOES_NOT_MATCH);
        }

    }

    public void login(CustomerLoginRequestDto dto) {
        Optional<Customer> optionalCustomer = customerRepository.findByEmailIgnoreCaseAndPassword(dto.getEmail(), dto.getPassword());
        if(optionalCustomer.isPresent()){
            if(optionalCustomer.get().isActivated()){
                optionalCustomer.get().setLoggedIn(true);
                update(optionalCustomer.get());
            } else{
                throw new YemekSepetiException(ErrorType.USER_NOT_ACTIVATED);
            }

        } else {
            throw new YemekSepetiException(ErrorType.CUSTOMER_NOT_FOUND);
        }
    }

    public List<CustomerFindAllResponseDto> findAllDto(){
        return customerRepository.findAll().stream().map(customer -> {
            return CustomerFindAllResponseDto.builder()
                    .id(customer.getId())
                    .name(customer.getName())
                    .email(customer.getEmail())
                    .address(customer.getAddress())
                    .phoneNumber(customer.getPhoneNumber())
                    .build();
        }).collect(Collectors.toList());
    }


}
