package com.pfms.user_management.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "Update User Status Request", description = "Update User Status Request")
public class UpdateUserStatusRequest {
    private Integer userId;
    private String status;
}
