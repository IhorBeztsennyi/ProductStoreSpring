package ua.goit.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.RoleDao;
import ua.goit.model.dto.RoleDto;

@Service
public class RoleConverter implements Converter<RoleDao, RoleDto>{
    @Override
    public RoleDto daoToDto(RoleDao type) {
        RoleDto role = new RoleDto();
        role.setId(type.getId());
        role.setName(type.getName());
        return role;
    }

    @Override
    public RoleDao dtoToDao(RoleDto type) {
        RoleDao role = new RoleDao();
        role.setId(type.getId());
        role.setName(type.getName());
        return role;
    }
}
