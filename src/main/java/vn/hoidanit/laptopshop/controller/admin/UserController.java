package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/")
    public String getHomePage(Model model) {
        String abc = "123456";
        model.addAttribute("hiep", abc);
        model.addAttribute("tuanhiepit", "From Controller");
        return "index";
    }

    @RequestMapping("/admin/user")
    public String getHomeUser(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/index";
    }

    @RequestMapping("/admin/user/create")
    public String getCreate(Model model) {
        String abc = "Tuan Hiep shop";
        model.addAttribute("title", abc);
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String getUser(Model model, @ModelAttribute("newUser") User user) {
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @RequestMapping("/admin/user/{id}")
    public String getUserDetail(Model model, @PathVariable Long id) {
        String abc = "Tuan Hiep Shop";
        User user = this.userService.getUserById(id);
        model.addAttribute("title", abc);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @RequestMapping("/admin/user/update/{id}")
    public String getUserEdit(Model model, @PathVariable Long id) {
        String abc = "Tuan Hiep Shop";
        model.addAttribute("title", abc);
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("newUser", currentUser);
        return "admin/user/edit";
    }

    @PostMapping("/admin/user/update")
    public String getUserUpdate(Model model, @ModelAttribute("newUser") User user) {
        String abc = "Tuan Hiep Shop";
        model.addAttribute("title", abc);
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setFullName(user.getFullName());
            currentUser.setAddress(user.getAddress());
            currentUser.setPhone(user.getPhone());
            user = this.userService.handleSaveUser(currentUser);
        }
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/delete/{id}")
    public String getUserDelete(Model model, @PathVariable Long id) {
        String abc = "Tuan Hiep Shop";
        model.addAttribute("title", abc);
        model.addAttribute("id", id);
        model.addAttribute("newUser", new User());
        return "admin/user/delete";
    }

    @PostMapping("/admin/user/delete")
    public String postDeleteUser(Model model, @ModelAttribute("newUser") User user) {
        String abc = "Tuan Hiep Shop";
        model.addAttribute("title", abc);
        this.userService.deleteUser(user.getId());
        return "redirect:/admin/user";
    }

}
