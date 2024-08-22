package org.karel.karel.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "loginForm";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(@ModelAttribute("user") UserData user, Model model){
        userDetailsService.loadUserByUsername(user.getUsername());
        return "redirect:/problems";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterForm(Model model){
        UserData user = new UserData();
        model.addAttribute("user", user);
        return "registerForm";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String userRegister(@ModelAttribute("user") UserData user, Model model){
        userDetailsService.saveUser(user);
        return "redirect:/problems";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String getProfile(Model model){
        UserProfile userProfile = userDetailsService.getUserProfile();
        model.addAttribute("user", userProfile);
        return "profile";
    }

    @RequestMapping(value = "/activate/{token}", method = RequestMethod.GET)
    public String getProfile(@PathVariable("token") String token, Model model){
        userDetailsService.activateByToken(token);
        return "userActivated";
    }
}
