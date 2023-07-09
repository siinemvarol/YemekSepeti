package com.sinem.controller;

import com.sinem.dto.request.CustomerLoginRequestDto;
import com.sinem.dto.request.CustomerRegisterRequestDto;
import com.sinem.dto.response.CustomerFindAllResponseDto;
import com.sinem.mapper.ICustomerMapper;
import com.sinem.repository.entity.Customer;
import com.sinem.repository.entity.Order;
import com.sinem.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping("/register")
    public ResponseEntity<Boolean> register(@RequestBody @Valid CustomerRegisterRequestDto dto){
        Customer customer = ICustomerMapper.INSTANCE.fromRegisterDtoToCustomer(dto);
        customerService.save(customer);
        return ResponseEntity.ok(true);
    }
    @PostMapping("/activate-account")
    public ResponseEntity<Boolean> activateAccount(Long id, int activationCode){
        customerService.activateAccount(id, activationCode);
        return ResponseEntity.ok(true);
    }

    @PostMapping("/login")
    public ResponseEntity<Boolean> login(@RequestBody @Valid CustomerLoginRequestDto dto){
        customerService.login(dto);
        return ResponseEntity.ok(true);
    }
    @GetMapping("/find-all")
    public ResponseEntity<List<CustomerFindAllResponseDto>> findAllDto(){
        return ResponseEntity.ok(customerService.findAllDto());
    }

}
