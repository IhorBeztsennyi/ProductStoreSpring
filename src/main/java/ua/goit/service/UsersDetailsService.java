package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.model.authoritation.UserPrincipal;
import ua.goit.model.dto.UserDto;

@Service
public class UsersDetailsService implements UserDetailsService {
    private UserService userService;

    @Autowired
    public UsersDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserDto user = userService.loadUserByEmail(email);
        return new UserPrincipal(user);
    }
}

