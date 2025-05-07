package com.pfms.user_management.service;


import com.pfms.user_management.request.PasswordResetRequest;
import com.pfms.user_management.request.UserLoginRequest;
import com.pfms.user_management.request.UserRegisterRequest;
import com.pfms.user_management.request.UpdateUserProfileRequest;
import com.pfms.user_management.response.UserDetailsResponse;

public interface UserService {
    UserDetailsResponse registerUser(UserRegisterRequest registerRequestDto);

    UserDetailsResponse updateProfile(UpdateUserProfileRequest updateProfileRequest,String loggedInEmail);

    void resetPassword(String loggedInEmail, PasswordResetRequest passwordResetRequest);

    UserDetailsResponse validateUser(UserLoginRequest loginRequest);
}
