package com.pfms.user_management.controller;

import com.pfms.user_management.constants.AuthConstants;
import com.pfms.user_management.exception.GlobalExceptionResponse;
import com.pfms.user_management.model.*;
import com.pfms.user_management.request.PasswordResetRequest;
import com.pfms.user_management.request.UpdateUserProfileRequest;
import com.pfms.user_management.request.UserLoginRequest;
import com.pfms.user_management.request.UserRegisterRequest;
import com.pfms.user_management.response.UserDetailsResponse;
import com.pfms.user_management.service.JwtService;
import com.pfms.user_management.service.UserService;
import com.pfms.user_management.util.ResponseUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.*;
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

    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @Operation(summary = "Register User")
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )

    @ApiResponse(
            responseCode = "409",
            description = "User already exists",
            content = @Content(
                    schema = @Schema(implementation = GlobalExceptionResponse.class)
            )
    )

    @PostMapping("/register")
    public ResponseEntity<UserManagementApiResponse<UserDetailsResponse>> register(@Valid @RequestBody UserRegisterRequest registerRequestDto) {
        UserDetailsResponse userDetailsResponse = userService.registerUser(registerRequestDto);
        return ResponseUtil.ok(userDetailsResponse);
    }

    @Operation(summary = "Login User")
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Request processed successfully"
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Invalid Credentials",
                            content = @Content(
                                    schema = @Schema(implementation = GlobalExceptionResponse.class)
                            )
                    )
            }
    )
    @PostMapping("/login")
    public ResponseEntity<UserManagementApiResponse<String>> login(@Valid @RequestBody UserLoginRequest loginRequest) {
        String token = userService.loginUser(loginRequest);
        return ResponseUtil.ok(token);
    }


    @GetMapping("/validate")
    @Operation(summary = "Validate Token")
    public String validateToken(@RequestParam("token") String token) {
        jwtService.validatingToken(token);
        return "Token is valid";
    }

    @PutMapping("/profile")
    @Operation(summary = "Update User Profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserManagementApiResponse<UserDetailsResponse>> updateProfile(@Valid @RequestBody UpdateUserProfileRequest updateProfileRequest) {
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        UserDetailsResponse userDetailsResponse = userService.updateProfile(updateProfileRequest, loggedInEmail);
        return ResponseUtil.ok(userDetailsResponse);
    }

    @PostMapping("/reset-password")
    @Operation(summary = "Reset Password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> resetPassword(@Valid @RequestBody PasswordResetRequest passwordResetRequest) {
        // Get email from authenticated JWT user
        String loggedInEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.resetPassword(loggedInEmail, passwordResetRequest);
        return ResponseEntity.ok(AuthConstants.PASSWORD_CHANGED_SUCCESSFULLY);
    }

}
