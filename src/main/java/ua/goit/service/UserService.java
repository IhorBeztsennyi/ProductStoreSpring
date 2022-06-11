package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.converter.UserConverter;
import ua.goit.exceptions.UserIsAlreadyExistsException;
import ua.goit.exceptions.UserNotFoundException;
import ua.goit.model.RolesEnum;
import ua.goit.model.dao.UserDao;
import ua.goit.model.dao.VendorDao;
import ua.goit.model.dto.UserDto;
import ua.goit.model.dto.VendorDto;
import ua.goit.repository.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UsersRepository userRepository;
    private final UserConverter userConverter;


    @Autowired
    public UserService(UsersRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public void save(UserDto user) {
        validateUser(user);
        user.setRole(RolesEnum.ROLE_USER);
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

    public List<UserDto> findAll(){
        List<UserDao> users = userRepository.findAllUsers();
        return users.stream()
                .map(userConverter::daoToDto)
                .collect(Collectors.toList());
    }
}
