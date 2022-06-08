package com.company.service.details;

import com.company.config.details.CustomProfileDetails;
import com.company.entity.ProfileEntity;
import com.company.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomProfileDetailsService implements UserDetailsService {

    private final ProfileRepository profileRepository;

    @Override
    public CustomProfileDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        ProfileEntity entity = profileRepository
                .findByPhoneNumberAndDeletedDateIsNull(phone)
                .orElseThrow(() -> new UsernameNotFoundException("Profile Not Found"));

        return new CustomProfileDetails(entity);
    }
}
