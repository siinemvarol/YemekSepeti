package com.sinem.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerRegisterRequestDto {
    @NotEmpty
    private String name;
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

}
