package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.model.dto.VendorDto;
import ua.goit.service.VendorService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/vendors")
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping(path = "/all")
    public String getAllVendors(Model model) {
        List<VendorDto> vendors = vendorService.findAll();
        model.addAttribute("vendors", vendors);
        return "vendorsList";
    }

    @GetMapping(path = "/form/find")
    public String getVendorForm() {
        return "findVendorForm";
    }

    @GetMapping(path = "/form/add")
    public String getNewVendorFrom(Model model) {
        return "addVendorForm";
    }

    @GetMapping(path = "/form/update")
    public String getVendorUpdateForm() {
        return "updateVendorForm";
    }

    @GetMapping(path = "/form/delete")
    public String getVendorDeleteForm() {
        return "deleteVendorForm";
    }

    @GetMapping(path = "/name/")
    public String getVendor(@RequestParam("name") String vendorName, Model model) {
        List<VendorDto> vendors = vendorService.findVendorByName(vendorName);
        model.addAttribute("vendors", vendors);
        return "findVendor";
    }

    @PostMapping(path = "/update")
    public ModelAndView updateVendor(@ModelAttribute("vendorDto") @Valid VendorDto vendorDto, BindingResult bindingResult,
                                     ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("updateVendorForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        vendorService.update(vendorDto);
        model.setViewName("vendorUpdated");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @PostMapping
    public ModelAndView addVendor(@ModelAttribute("vendorDto") @Valid VendorDto vendorDto, BindingResult bindingResult,
                                  ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("addVendorForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        vendorService.save(vendorDto);
        model.setViewName("vendorAdded");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @PostMapping(path = "/delete")
    public ModelAndView deleteVendor(@ModelAttribute("vendorDto") @Valid VendorDto vendorDto, BindingResult bindingResult,
                                     ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("deleteVendorForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        vendorService.deleteById(vendorDto.getId());
        model.setViewName("vendorDeleted");
        return model;
    }

    @ModelAttribute
    public VendorDto getDefaultVendorDto() {
        return new VendorDto();
    }
}
