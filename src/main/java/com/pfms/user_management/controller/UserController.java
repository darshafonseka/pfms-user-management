package com.pfms.user_management.controller;

import com.pfms.user_management.constants.AuthConstants;
import com.pfms.user_management.exception.GlobalExceptionResponse;
import com.pfms.user_management.model.*;
import com.pfms.user_management.entity.User;
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
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@Tag(
        name = "PFMS User"
)
@RestController
@RequestMapping(value = "/api/user", produces = {MediaType.APPLICATION_JSON_VALUE})
public class UserController {
    @Autowired
    private UserService userRegisterService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Operation(summary = "Register User")
    @ApiResponse(
            responseCode = "201",
            description = "Account created successfully"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "409",
                            description = "User already exists",
                            content = @Content(
                                    schema = @Schema(implementation = GlobalExceptionResponse.class)
                            )
                    )
            }
    )
    @PostMapping("/register")
    public ResponseEntity<UserManagementApiResponse<UserDetailsResponse>> register(@RequestBody UserRegisterRequest registerRequestDto) {
        UserDetailsResponse userDetailsResponse = userRegisterService.registerUser(registerRequestDto);
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
    public ResponseEntity<UserManagementApiResponse<String>> login(@RequestBody UserLoginRequest loginRequestDto) {
        User userDetails = userRegisterService.getUserDetailsByEmail(loginRequestDto.getEmail());

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDetails.getUsername(), loginRequestDto.getPassword()));
        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(userDetails.getUsername(), String.valueOf(userDetails.getRole()));
            return ResponseUtil.ok(token);
        } else {
            return ResponseUtil.badRequest(AuthConstants.INVALID_CREDENTIALS);
        }
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
    public ResponseEntity<UserManagementApiResponse<UserDetailsResponse>> updateProfile(@RequestBody UpdateUserProfileRequest updateProfileRequestDto) {
        UserDetailsResponse userDetailsResponse = userRegisterService.updateProfile(updateProfileRequestDto);
        return ResponseUtil.ok(userDetailsResponse);
    }

}
