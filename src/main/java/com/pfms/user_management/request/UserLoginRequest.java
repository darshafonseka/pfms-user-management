package com.pfms.user_management.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequest {

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Email address required")
    @Schema(description = "Email address",  example = "pfms@example.com")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password required")
    @Schema(description = "Password", example = "password")
    @Size(min = 8, message = "Password should be at least 8 characters")
    private String password;
}
