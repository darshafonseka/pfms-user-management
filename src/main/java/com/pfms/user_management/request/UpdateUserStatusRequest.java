package com.pfms.user_management.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Schema(name = "Update User Status Request", description = "Update User Status Request")
public class UpdateUserStatusRequest {

    @Schema(description = "User Id",  example = "1")
    @NotNull(message = "User Id required")
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Schema(description = "Status",  example = "ACTIVE")
    @NotEmpty(message = "Status required")
    @Column(name = "status", nullable = false)
    private String status;
}
