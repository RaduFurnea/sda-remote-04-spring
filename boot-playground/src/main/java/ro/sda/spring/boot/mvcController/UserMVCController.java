package ro.sda.spring.boot.mvcController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.sda.spring.boot.dto.UserRegisterDTO;
import ro.sda.spring.boot.entity.User;
import ro.sda.spring.boot.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class UserMVCController {

    private final UserService userService;

    @Autowired
    public UserMVCController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/user/register")
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegisterDTO userDTO,
                                      BindingResult result) {

        Optional<User> optExisting = userService.getUserByUsername(userDTO.getUsername());
        if (optExisting.isPresent()) {
            result.rejectValue("username", null, "There is already an account registered with that username");
        }

        if (!userDTO.getPassword().equals(userDTO.getConfirmPassword())) {
            result.rejectValue("password", null, "Passwords do not match");
        }

        if (result.hasErrors()) {
            return "register";
        }

        userService.saveUser(userDTO);
        return "redirect:/register?success";
    }


}
