package com.pfms.user_management.service.implementation;

import com.pfms.user_management.enums.Role;
import com.pfms.user_management.enums.UserManagementError;
import com.pfms.user_management.enums.UserStatus;
import com.pfms.user_management.exception.ApplicationException;
import com.pfms.user_management.request.PasswordResetRequest;
import com.pfms.user_management.request.UserLoginRequest;
import com.pfms.user_management.request.UserRegisterRequest;
import com.pfms.user_management.request.UpdateUserProfileRequest;
import com.pfms.user_management.entity.User;
import com.pfms.user_management.repository.PFMSUserRepo;
import com.pfms.user_management.response.UserDetailsResponse;
import com.pfms.user_management.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private static final Log logger = LogFactory.getLog(UserServiceImpl.class);
    
    private final PFMSUserRepo pfmsUserRepo;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    public UserServiceImpl(PFMSUserRepo pfmsUserRepo) {
        this.pfmsUserRepo = pfmsUserRepo;
    }

    public UserDetailsResponse registerUser(UserRegisterRequest registerRequestDto) throws ApplicationException {
        if (pfmsUserRepo.findByEmail(registerRequestDto.getEmail()) != null) {
            throw new ApplicationException(UserManagementError.USER_EXISTS);
        }
        var user = User.builder()
                .email(registerRequestDto.getEmail())
                .username(registerRequestDto.getUsername())
                .password(registerRequestDto.getPassword())
                .status(UserStatus.ACTIVE)
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        pfmsUserRepo.save(user);
        return UserDetailsResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .status(user.getStatus().toString())
                .build();
    }

    @Override
    public UserDetailsResponse updateProfile(UpdateUserProfileRequest updateProfileRequestDto, String loggedInEmail) throws ApplicationException {
        User user = pfmsUserRepo.findById(Long.valueOf(updateProfileRequestDto.getUserId())).orElseThrow(
                () -> new ApplicationException(UserManagementError.USER_NOT_FOUND)
        );
        if (!user.getEmail().equals(loggedInEmail)) {
            throw new ApplicationException(UserManagementError.UNAUTHORIZED_ACCESS);
        }
        if (updateProfileRequestDto.getUsername() != null) {
            user.setUsername(updateProfileRequestDto.getUsername());
        }
        if (updateProfileRequestDto.getEmail() != null) {
            user.setEmail(updateProfileRequestDto.getEmail());
        }
        user.setUpdatedAt(LocalDateTime.now());
        pfmsUserRepo.save(user);
        return UserDetailsResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .status(user.getStatus().toString())
                .build();
    }

    @Override
    public void resetPassword(String loggedInEmail, PasswordResetRequest passwordResetRequest) {
        logger.info("loggedInEmail: " + loggedInEmail);
        User user = pfmsUserRepo.findByEmail(loggedInEmail);
        if(user == null) {
            throw new ApplicationException(UserManagementError.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(passwordResetRequest.getCurrentPassword(), user.getPassword())) {
            throw new ApplicationException(UserManagementError.CURRENT_PASSWORD_INCORRECT);
        }
        if (passwordEncoder.matches(passwordResetRequest.getNewPassword(), user.getPassword())) {
            throw new ApplicationException(UserManagementError.NEW_PASSWORD_SAME_AS_CURRENT_PASSWORD);
        }
        user.setPassword(passwordEncoder.encode(passwordResetRequest.getNewPassword()));
        pfmsUserRepo.save(user);

    }

    @Override
    public UserDetailsResponse validateUser(UserLoginRequest loginRequest) {
        User user = pfmsUserRepo.findByEmail(loginRequest.getEmail());
        if (user == null) {
            throw new ApplicationException(UserManagementError.USER_NOT_FOUND);
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new ApplicationException(UserManagementError.INCORRECT_PASSWORD);
        }
        return UserDetailsResponse.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .role(user.getRole().toString())
                .status(user.getStatus().toString())
                .build();
    }
}
