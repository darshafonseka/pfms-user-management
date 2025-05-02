package com.pfms.user_management.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "User Details Response", description = "User Details Response")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDetailsResponse {
    private Integer id;
    private String username;
    private String email;
    private String role;
    private String status;
}
