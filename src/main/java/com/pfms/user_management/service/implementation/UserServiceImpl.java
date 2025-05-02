package com.pfms.user_management.service.implementation;

import com.pfms.user_management.enums.UserManagementError;
import com.pfms.user_management.exception.ApplicationException;
import com.pfms.user_management.request.UserRegisterRequest;
import com.pfms.user_management.request.UpdateUserProfileRequest;
import com.pfms.user_management.entity.User;
import com.pfms.user_management.repository.PFMSUserRepo;
import com.pfms.user_management.response.UserDetailsResponse;
import com.pfms.user_management.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private final PFMSUserRepo pfmsUserRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserServiceImpl(PFMSUserRepo pfmsUserRepo) {
        this.pfmsUserRepo = pfmsUserRepo;
    }

    public UserDetailsResponse registerUser(UserRegisterRequest registerRequestDto) throws ApplicationException {
        if (pfmsUserRepo.findByEmail(registerRequestDto.getEmail()) != null) {
            throw new ApplicationException(UserManagementError.USER_EXIST);
        }
        var user = User.builder()
                .email(registerRequestDto.getEmail())
                .username(registerRequestDto.getUsername())
                .password(registerRequestDto.getPassword())
                .status(User.Status.ACTIVE)
                .role(User.Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        user.setPassword(encoder.encode(user.getPassword()));
        pfmsUserRepo.save(user);
        return UserDetailsResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .status(user.getStatus().toString())
                .build();
    }

    @Override
    public User getUserDetailsByEmail(String email) throws ApplicationException {
        User user = pfmsUserRepo.findByEmail(email);
        if (user == null) {
            throw new ApplicationException(UserManagementError.USER_NOT_FOUND);
        }
        return user;
    }


    @Override
    public UserDetailsResponse updateProfile(UpdateUserProfileRequest updateProfileRequestDto) throws ApplicationException {
        User user = pfmsUserRepo.findById(Long.valueOf(updateProfileRequestDto.getUserId())).orElseThrow(
                () -> new ApplicationException(UserManagementError.USER_NOT_FOUND)
        );
        if (updateProfileRequestDto.getUsername() != null) {
            user.setUsername(updateProfileRequestDto.getUsername());
        }
        if (updateProfileRequestDto.getEmail() != null) {
            user.setEmail(updateProfileRequestDto.getEmail());
        }
        user.setUpdatedAt(LocalDateTime.now());
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
