package com.pfms.user_management.controller;

import com.pfms.user_management.constants.AuthConstants;
import com.pfms.user_management.exception.GlobalExceptionResponse;
import com.pfms.user_management.model.ApiResponse;
import com.pfms.user_management.request.PasswordResetRequest;
import com.pfms.user_management.request.UpdateUserProfileRequest;
import com.pfms.user_management.request.UserLoginRequest;
import com.pfms.user_management.request.UserRegisterRequest;
import com.pfms.user_management.response.UserDetailsResponse;
import com.pfms.user_management.service.UserService;
import com.pfms.user_management.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "PFMS User"
)
@RestController
@RequestMapping(value = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {

    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Register User")
    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )

    @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "409",
            description = "User already exists",
            content = @Content(
                    schema = @Schema(implementation = GlobalExceptionResponse.class)
            )
    )

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDetailsResponse>> register(@Valid @RequestBody UserRegisterRequest registerRequestDto) {
        UserDetailsResponse userDetailsResponse = userService.registerUser(registerRequestDto);
        return ResponseUtil.ok(userDetailsResponse);
    }


    @PutMapping("/profile")
    @Operation(summary = "Update User Profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<UserDetailsResponse>> updateProfile(@Valid @RequestBody UpdateUserProfileRequest updateProfileRequest) {
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDetailsResponse userDetailsResponse = userService.updateProfile(updateProfileRequest, loggedInEmail);
        return ResponseUtil.ok(userDetailsResponse);
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset Password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<String>> resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        // Get email from authenticated JWT user
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        userService.resetPassword(loggedInEmail, passwordResetRequest);
        return ResponseUtil.ok(AuthConstants.PASSWORD_CHANGED_SUCCESSFULLY);
    }

    @PostMapping("/login-validate")
    @Operation(summary = "Validate Token")
    @PreAuthorize("hasRole('USER')")
    public UserDetailsResponse validateUser(@RequestBody UserLoginRequest loginRequest) {
        return userService.validateUser(loginRequest);
    }

}
