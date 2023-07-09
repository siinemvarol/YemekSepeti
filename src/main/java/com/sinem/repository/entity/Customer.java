package com.sinem.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tbl_customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    private String address;
    private String phoneNumber;
    @NotEmpty
    @Size(min = 8, max = 64)
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=*!])(?=\\S+$).{8,}$")
    private String password;
    @NotNull
    private Long cardDetails;
    private Double balance;
    private int activationCode;
    @Builder.Default
    private boolean isActivated = false;
    @Builder.Default
    private boolean isLoggedIn = false;
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

}
