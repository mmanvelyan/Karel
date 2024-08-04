package org.karel.karel.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    private final UserService userDetailsService;

    public UserController(UserService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(@ModelAttribute("user") User user, Model model){
        userDetailsService.loadUserByUsername(user.getUsername());
        return "redirect:/problems";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String userRegister(@ModelAttribute("user") User user, Model model){
        userDetailsService.saveUser(user);
        return "redirect:/problems";
    }
}
