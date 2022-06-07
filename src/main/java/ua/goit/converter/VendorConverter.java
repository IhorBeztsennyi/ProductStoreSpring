package ua.goit.converter;

import org.springframework.stereotype.Service;
import ua.goit.model.dao.VendorDao;
import ua.goit.model.dto.VendorDto;

@Service
public class VendorConverter implements Converter<VendorDao, VendorDto> {
    @Override
    public VendorDto daoToDto(VendorDao type) {
        VendorDto vendor = new VendorDto();
        vendor.setId(type.getId());
        vendor.setName(type.getName());
       return vendor;
    }

    @Override
    public VendorDao dtoToDao(VendorDto type) {
        VendorDao vendor = new VendorDao();
        vendor.setId(type.getId());
        vendor.setName(type.getName());
        return vendor;
    }
}
