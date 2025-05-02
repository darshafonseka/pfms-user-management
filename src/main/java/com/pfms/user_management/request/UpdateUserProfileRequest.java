package com.pfms.user_management.request;

import io.swagger.v3.oas.annotations.media.Schema;
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
    private Integer userId;
    private String username;
    private String email;
    private String password;
}
