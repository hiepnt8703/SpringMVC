package vn.hoidanit.laptopshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String test = this.userService.handleHello();
        model.addAttribute("hiep", test);
        model.addAttribute("tuanhiepit", "From Controller");
        return "hello";
    }

    @RequestMapping("/admin/user")
    public String getCreate(Model model) {
        String abc = "Tuan Hiep shop";
        model.addAttribute("title", abc);
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create1", method = RequestMethod.POST)
    public String getUser(Model model, @ModelAttribute("newUser") User user) {
        System.out.println("Run here" + user);
        return "hello";
    }

}
