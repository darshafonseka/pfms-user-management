package com.pfms.user_management.service.implementation;

import com.pfms.user_management.entity.User;
import com.pfms.user_management.enums.UserManagementError;
import com.pfms.user_management.enums.UserStatus;
import com.pfms.user_management.exception.ApplicationException;
import com.pfms.user_management.repository.PFMSUserRepo;
import com.pfms.user_management.request.UpdateUserStatusRequest;
import com.pfms.user_management.response.UserDetailsResponse;
import com.pfms.user_management.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final PFMSUserRepo pfmsUserRepo;

    public AdminServiceImpl(PFMSUserRepo pfmsUserRepo) {
        this.pfmsUserRepo = pfmsUserRepo;
    }

    @Override
    public List<UserDetailsResponse> getAllUsers() {
        List<User> users = pfmsUserRepo.findAll();
        if (users.isEmpty()) {
            throw new ApplicationException(UserManagementError.RECORD_NOT_FOUND);
        }
        return users.stream().map(user -> {
            UserDetailsResponse userDetailsResponse = new UserDetailsResponse();
            userDetailsResponse.setId(user.getId());
            userDetailsResponse.setUsername(user.getUsername());
            userDetailsResponse.setEmail(user.getEmail());
            userDetailsResponse.setRole(user.getRole().name());
            userDetailsResponse.setStatus(user.getStatus().name());
            return userDetailsResponse;
        }).toList();
    }

    @Override
    public UserDetailsResponse updateUserStatus(UpdateUserStatusRequest updateUserStatusRequest) {
        User user = pfmsUserRepo.findById(Long.valueOf(updateUserStatusRequest.getUserId()))
                .orElseThrow(() -> new ApplicationException(UserManagementError.USER_NOT_FOUND)); // Throw ApplicationException if user not found

        if (user.getStatus().name().equals(updateUserStatusRequest.getStatus())) {
            throw new ApplicationException(UserManagementError.USER_STATUS_ALREADY_UPDATED);
        }

        user.setStatus(UserStatus.valueOf(updateUserStatusRequest.getStatus()));
        pfmsUserRepo.save(user);
        return UserDetailsResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .status(user.getStatus().toString())
                .build();
    }
}
