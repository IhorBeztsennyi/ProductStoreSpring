package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.acls.model.AlreadyExistsException;
import org.springframework.stereotype.Service;
import ua.goit.converter.VendorConverter;
import ua.goit.exceptions.VendorIsAlreadyExistsException;
import ua.goit.exceptions.VendorNotFoundException;
import ua.goit.model.dao.VendorDao;
import ua.goit.model.dto.VendorDto;
import ua.goit.repository.VendorRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class VendorService {

    private final VendorRepository vendorRepository;
    private final VendorConverter vendorConverter;

    @Autowired
    public VendorService(VendorRepository vendorRepository, VendorConverter vendorConverter) {
        this.vendorRepository = vendorRepository;
        this.vendorConverter = vendorConverter;
    }

    public VendorDto findById(UUID id) {
        return vendorConverter.daoToDto(vendorRepository.findById(id).orElseThrow(() -> new VendorNotFoundException(String.format("Vendor with id %s is not found", id))));
    }

    public VendorDto findByName(String name) {
        return vendorConverter.daoToDto(vendorRepository.findByName(name).orElseThrow(() -> new VendorNotFoundException(String.format("Vendor with name %s is not found", name))));
    }

    public void save(VendorDto vendor) {
        Optional<VendorDao> vendorDao = vendorRepository.findByName(vendor.getName());
        if (vendorDao.isEmpty()) {
            vendorRepository.save(vendorConverter.dtoToDao(vendor));
        } else {
            throw new VendorIsAlreadyExistsException(String.format("Vendor with name %s already exists", vendor.getName()));
        }
    }

}
