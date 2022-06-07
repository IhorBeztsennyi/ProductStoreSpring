package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.converter.RoleConverter;
import ua.goit.converter.UserConverter;
import ua.goit.exceptions.UserIsAlreadyExistsException;
import ua.goit.exceptions.UserNotFoundException;
import ua.goit.model.dto.RoleDto;
import ua.goit.model.dto.UserDto;
import ua.goit.repository.RoleRepository;
import ua.goit.repository.UsersRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    private final UsersRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;
    private final RoleConverter roleConverter;


    @Autowired
    public UserService(UsersRepository userRepository, UserConverter userConverter, RoleRepository roleRepository, RoleConverter roleConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleRepository = roleRepository;
        this.roleConverter = roleConverter;
    }

    public void save(UserDto user) {
        validateUser(user);
        Set<RoleDto> roles = new HashSet<>();
        RoleDto role = roleConverter.daoToDto(roleRepository.findByName("user"));
        roles.add(role);
        user.setRoles(roles);
        userRepository.save(userConverter.dtoToDao(user));
    }

    private void validateUser(UserDto dto) {
        userRepository.findByEmail(dto.getEmail())
                .ifPresent((user) -> {
                    throw new UserIsAlreadyExistsException("User with email " + user.getEmail() + " is already exists");
                });
    }

    public UserDto loadUserByEmail(String email) {
        return userConverter.daoToDto(userRepository.findByEmail(email).orElseThrow(() ->
                new UserNotFoundException(String.format("User with email %s not exists", email))));
    }
}
