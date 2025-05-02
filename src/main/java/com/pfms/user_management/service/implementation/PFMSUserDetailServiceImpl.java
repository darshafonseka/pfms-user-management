package com.pfms.user_management.service.implementation;


import com.pfms.user_management.entity.User;
import com.pfms.user_management.entity.UserPrinciple;
import com.pfms.user_management.enums.UserManagementError;
import com.pfms.user_management.exception.ApplicationException;
import com.pfms.user_management.repository.PFMSUserRepo;
import com.pfms.user_management.service.PFMSUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PFMSUserDetailServiceImpl implements PFMSUserDetailsService {

    private final PFMSUserRepo pfmsUserRepo;

    public PFMSUserDetailServiceImpl(PFMSUserRepo pfmsUserRepo) {
        this.pfmsUserRepo = pfmsUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username ) throws ApplicationException {
        User user = pfmsUserRepo.findByUsername(username);
        if (user == null) {
            throw new ApplicationException(UserManagementError.USER_NOT_FOUND);
        }
        return new UserPrinciple(user);
    }
}
