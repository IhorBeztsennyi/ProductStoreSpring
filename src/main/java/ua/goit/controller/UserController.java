package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.goit.exceptions.UserIsAlreadyExistsException;
import ua.goit.model.dto.UserDto;
import ua.goit.model.dto.VendorDto;
import ua.goit.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/users")
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(path = "/registration")
    public String getRegistrationForm() {
        return "registration";
    }

    @PostMapping(path = "/registration")
    public String registerUser(@ModelAttribute("userForm") @Valid UserDto user, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);
        } catch (UserIsAlreadyExistsException ex) {
            model.addAttribute("message", ex.getMessage());
            return "registration";
        }

        return "login";
    }

    @ModelAttribute("userForm")
    public UserDto getDefaultUserDto() {
        return new UserDto();
    }

    @GetMapping(path = "/all")
    public String getAllUsers(Model model) {
        List<UserDto> users = userService.findAll();
        model.addAttribute("users", users);
        return "usersList";
    }

    @GetMapping(path = "/form/find")
    public String getUsersForm() {
        return "findUserForm";
    }
    @GetMapping(path = "/name/")
    public String getVendor(@RequestParam("email") String email, Model model) {
        List<VendorDto> vendors = userService.findUserByName(vendorName);
        model.addAttribute("vendors", vendors);
        return "findVendor";
    }
}
