package ua.goit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.goit.exceptions.UserIsAlreadyExistsException;
import ua.goit.model.dto.UserDto;
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
    public String getUser(@RequestParam("email") String email, Model model) {
        List<UserDto> users = userService.findUserByEmail(email);
        model.addAttribute("users", users);
        return "findUser";
    }

    @GetMapping(path = "/form/update")
    public String getUserUpdateForm() {
        return "updateUserForm";
    }

    @GetMapping(path = "/form/delete")
    public String getUserDeleteForm() {
        return "deleteUserForm";
    }

    @PutMapping
    public ModelAndView updateUser(@ModelAttribute("userForm") @Valid UserDto userDto, BindingResult bindingResult,
                                   ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("updateUserForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        userService.update(userDto);
        model.setViewName("userUpdated");
        model.setStatus(HttpStatus.CREATED);
        return model;
    }

    @DeleteMapping
    public ModelAndView deleteUser(@ModelAttribute("userForm") @Valid UserDto userDto, BindingResult bindingResult,
                                   ModelAndView model) {
        if (bindingResult.hasErrors()) {
            model.setViewName("deleteUserForm");
            model.setStatus(HttpStatus.BAD_REQUEST);
            return model;
        }
        userService.deleteById(userDto.getId());
        model.setViewName("userDeleted");
        return model;
    }
}
