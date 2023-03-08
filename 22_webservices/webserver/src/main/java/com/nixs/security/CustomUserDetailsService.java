package com.nixs.security;

import com.nixs.model.dto.UserDto;
import com.nixs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        Optional<UserDto> userOptional = userService.getUserByName(name);
        if (userOptional.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        UserDto userDto = userOptional.get();
        org.springframework.security.core.userdetails.User.UserBuilder builder =
                org.springframework.security.core.userdetails.User.withUsername(name);

        builder.password(userDto.getPassword());
        builder.authorities(userDto.getRoleName());
        return builder.build();
    }
}

