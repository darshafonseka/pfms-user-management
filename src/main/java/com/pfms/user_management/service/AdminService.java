package com.pfms.user_management.service;

import com.pfms.user_management.request.UpdateUserStatusRequest;
import com.pfms.user_management.response.UserDetailsResponse;

import java.util.List;

public interface AdminService {
    List<UserDetailsResponse> getAllUsers();

    UserDetailsResponse updateUserStatus(UpdateUserStatusRequest updateUserStatusRequest);
}
