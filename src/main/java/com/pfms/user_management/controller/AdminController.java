package com.pfms.user_management.controller;

import com.pfms.user_management.model.UserManagementApiResponse;
import com.pfms.user_management.request.UpdateUserStatusRequest;
import com.pfms.user_management.response.UserDetailsResponse;
import com.pfms.user_management.service.AdminService;
import com.pfms.user_management.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/users")
    @Operation(summary = "Get All Users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserManagementApiResponse<List<UserDetailsResponse>>> getAllUsers() {
        List<UserDetailsResponse> userDetails = adminService.getAllUsers();
        return ResponseUtil.ok(userDetails);
    }
    @PutMapping("/user/status")
    @Operation(summary = "Update User Status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserManagementApiResponse<UserDetailsResponse>> updateUserStatus( @Valid @RequestBody UpdateUserStatusRequest updateUserStatusRequest) {
        UserDetailsResponse userDetailsResponse = adminService.updateUserStatus(updateUserStatusRequest);
        return ResponseUtil.ok(userDetailsResponse);
    }

}
