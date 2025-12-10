package com.alvirg.employee_recruiter.user;

import com.alvirg.employee_recruiter.user.request.ProfileUpdateRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public void mergeUserInfo(final User savedUser, final ProfileUpdateRequest request) {
        if(StringUtils.isNotBlank(request.getFirstName())
                && !savedUser.getFirstName().equals(request.getFirstName())){
            savedUser.setFirstName(request.getFirstName());
        }

        if(StringUtils.isNotBlank(request.getLastName())
                && !savedUser.getLastName().equals(request.getLastName())){
            savedUser.setLastName(request.getLastName());
        }

        if(request.getDateOfBirth() != null
                && !request.getDateOfBirth().equals(savedUser.getDateOfBirth()))
            savedUser.setDateOfBirth(request.getDateOfBirth());
        }
}
