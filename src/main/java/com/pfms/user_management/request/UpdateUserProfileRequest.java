package com.pfms.user_management.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(name = "Update Profile Request", description = "Update Profile Request")
public class UpdateUserProfileRequest {
    @NotNull(message = "User Id required")
    @Schema(description = "User Id",  example = "1")
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Schema(description = "Username",  example = "LmsUser")
    @Column(name = "username")
    private String username;


    @Schema(description = "Email address",  example = "lms@example.com")
    @Column(name = "email", unique = true)
    private String email;

}
