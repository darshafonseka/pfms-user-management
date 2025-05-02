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
@Schema(description = "Register Request DTO")
public class UserRegisterRequest {

    @Column(name = "username", nullable = false)
    @NotEmpty(message = "Username required")
    @Schema(description = "Username", required = true, example = "LmsUser")
    private String username;

    @Column(name = "email", unique = true, nullable = false)
    @Email(message = "Enter valid email")
    @NotEmpty(message = "Email address required")
    @Schema(description = "Email address", required = true, example = "lms@example.com")
    private String email;

    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password required")
    @Size(min = 8, message = "Password should be at least 8 characters")
    @Schema(description = "Password", required = true, example = "password")
    private String password;

    @Column(name = "role", nullable = false)
    @NotEmpty(message = "Role required")
    @Schema(description = "Role", required = true, example = "USER")
    private String role;
}
