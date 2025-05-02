package com.pfms.user_management.service;


import com.pfms.user_management.request.PasswordResetRequest;
import com.pfms.user_management.request.UserLoginRequest;
import com.pfms.user_management.request.UserRegisterRequest;
import com.pfms.user_management.request.UpdateUserProfileRequest;
import com.pfms.user_management.entity.User;
import com.pfms.user_management.response.UserDetailsResponse;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService {
    UserDetailsResponse registerUser(UserRegisterRequest registerRequestDto);

    String loginUser(UserLoginRequest loginRequest);
    User getUserDetailsByEmail(String email) throws UsernameNotFoundException;
    UserDetailsResponse updateProfile(UpdateUserProfileRequest updateProfileRequest,String loggedInEmail);

    void resetPassword(String loggedInEmail, PasswordResetRequest passwordResetRequest);
}
