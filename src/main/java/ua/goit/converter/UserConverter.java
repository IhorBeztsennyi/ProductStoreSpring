package ua.goit.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.model.dao.UserDao;
import ua.goit.model.dto.UserDto;

import java.util.stream.Collectors;

@Service
public class UserConverter implements Converter<UserDao, UserDto>{

    private final RoleConverter roleConverter;

    @Autowired
    public UserConverter(RoleConverter roleConverter) {
        this.roleConverter = roleConverter;
    }

    @Override
    public UserDto daoToDto(UserDao type) {
        UserDto user = new UserDto();
        user.setId(type.getId());
        user.setEmail(type.getEmail());
        user.setPassword(type.getPassword());
        user.setFirstName(type.getFirstName());
        user.setLastName(type.getLastName());
        user.setRoles(type.getRoles().stream()
                .map(roleConverter::daoToDto)
                .collect(Collectors.toSet()));
        return user;
    }

    @Override
    public UserDao dtoToDao(UserDto type) {
        UserDao user = new UserDao();
        user.setId(type.getId());
        user.setEmail(type.getEmail());
        user.setPassword(type.getPassword());
        user.setFirstName(type.getFirstName());
        user.setLastName(type.getLastName());
        user.setRoles(type.getRoles().stream()
                .map(roleConverter::dtoToDao)
                .collect(Collectors.toSet()));
        return user;
    }
}
