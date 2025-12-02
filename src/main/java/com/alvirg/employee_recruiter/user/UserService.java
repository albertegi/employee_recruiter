package com.alvirg.employee_recruiter.user;

import com.alvirg.employee_recruiter.user.request.ChangePasswordRequest;
import com.alvirg.employee_recruiter.user.request.ProfileUpdateRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public void updateProfileInfo(ProfileUpdateRequest request, String userId);

    public void changePassword(ChangePasswordRequest request, String userId);

    public void deactivateAccount(String userId);

    public void reactivateAccount(String userId);

    public void deleteAccount(String userId);
}
