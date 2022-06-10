package ua.goit.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.goit.model.dao.UserDao;
import ua.goit.model.dto.UserDto;


@Service
public class UserConverter implements Converter<UserDao, UserDto> {

    @Override
    public UserDto daoToDto(UserDao type) {
        UserDto user = new UserDto();
        user.setId(type.getId());
        user.setEmail(type.getEmail());
        user.setPassword(type.getPassword());
        user.setFirstName(type.getFirstName());
        user.setLastName(type.getLastName());
        user.setRole(type.getRole());
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
        user.setRole(type.getRole());
        return user;
    }
}
