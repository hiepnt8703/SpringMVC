package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.UploadService;
import vn.hoidanit.laptopshop.service.UserService;

@Controller
public class UserController {
    private final UserService userService;
    private final UploadService uploadService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, UploadService uploadService,
            PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.uploadService = uploadService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/admin/user")
    public String getHomeUser(Model model) {
        List<User> users = this.userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin/user/index";
    }

    @GetMapping("/admin/user/create")
    public String getCreate(Model model) {
        String abc = "12345";
        model.addAttribute("title", abc);
        model.addAttribute("newUser", new User());
        return "admin/user/create";
    }

    @PostMapping("/admin/user/create")
    public String getUser(Model model,
            @ModelAttribute("newUser") @Valid User user,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile file) {
        // validation
        if (bindingResult.hasErrors()) {
            return "admin/user/create";
        }
        // end
        String avatar = this.uploadService.handleUploadSave(file, "avatar");
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setAvatar(avatar);
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName(user.getRole().getName()));
        this.userService.handleSaveUser(user);
        return "redirect:/admin/user";
    }

    @GetMapping("/admin/user/{id}")
    public String getUserDetail(Model model,@PathVariable Long id) {
        String abc = "Tuan Hiep Shop";
        User user = this.userService.getUserById(id);
        model.addAttribute("title", abc);
        model.addAttribute("user", user);
        model.addAttribute("id", id);
        return "admin/user/detail";
    }

    @GetMapping("/admin/user/update/{id}")
    public String getUserEdit(Model model, @PathVariable Long id) {
        String abc = "Tuan Hiep Shop";
        model.addAttribute("title", abc);
        User currentUser = this.userService.getUserById(id);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("newUser", currentUser);
        return "admin/user/edit";
    }

    @PostMapping("/admin/user/update")
    public String getUserUpdate(Model model, @ModelAttribute("newUser") User user,
            @RequestParam("imageFile") MultipartFile file) {
        String abc = "Tuan Hiep Shop";
        String avatar = this.uploadService.handleUploadSave(file, "avatar");
        model.addAttribute("title", abc);
        User currentUser = this.userService.getUserById(user.getId());
        if (currentUser != null) {
            currentUser.setFullName(user.getFullName());
            currentUser.setAddress(user.getAddress());
            currentUser.setPhone(user.getPhone());
            currentUser.setRole(this.userService.getRoleByName(user.getRole().getName()));
            currentUser.setAvatar(avatar);
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
