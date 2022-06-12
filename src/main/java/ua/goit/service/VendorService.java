package ua.goit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ua.goit.converter.VendorConverter;
import ua.goit.exceptions.VendorIsAlreadyExistsException;
import ua.goit.exceptions.VendorNotFoundException;
import ua.goit.model.dao.VendorDao;
import ua.goit.model.dto.VendorDto;
import ua.goit.repository.VendorRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<VendorDto> findAll() {
        List<VendorDao> vendors = vendorRepository.findAllVendors();
        return vendors.stream()
                .map(vendorConverter::daoToDto)
                .collect(Collectors.toList());
    }


    public List<VendorDto> findVendorByName(String name) {
        List<VendorDao> vendors = vendorRepository.findByNameList(name);
        return vendors.stream()
                .map(vendorConverter::daoToDto)
                .collect(Collectors.toList());
    }

    public void save(VendorDto vendor) {
        Optional<VendorDao> vendorDao = vendorRepository.findByName(vendor.getName());
        if (vendorDao.isEmpty()) {
            vendorRepository.save(vendorConverter.dtoToDao(vendor));
        } else {
            throw new VendorIsAlreadyExistsException(String.format("Vendor with name %s is already exists", vendor.getName()));
        }
    }

    public void update(VendorDto vendor) {
        Optional<VendorDao> vendorDao = vendorRepository.findById(vendor.getId());
        if (vendorDao.isPresent()) {
            VendorDao old = vendorDao.get();
            VendorDao dao = new VendorDao();
            dao.setId(old.getId());
            dao.setName(vendor.getName());
            vendorRepository.save(dao);
        } else {
            throw new VendorIsAlreadyExistsException(String.format("Vendor with id %s is not exists", vendor.getId()));
        }
    }

    public void deleteById(UUID id) {
        try {
            vendorRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new VendorIsAlreadyExistsException(String.format("Vendor with id %s is not exists", id));
        }
    }
}
