package vn.hoidanit.laptopshop.controller.client;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.domain.dto.RegisterDTO;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UserService;

import java.util.List;

@Controller
public class HomepageController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ProductService productService;

    public HomepageController(UserService userService, PasswordEncoder passwordEncoder, ProductService productService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.productService = productService;
    }

    @GetMapping("/")
    public String getHomePage(Model model, @RequestParam(value = "page" , defaultValue = "1") int page) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Product> prs = this.productService.fetchProducts(pageable);
        List<Product> products = prs.getContent();
        model.addAttribute("products", products);
        return "client/homepage/show";
    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        model.addAttribute("registerUser", new RegisterDTO());
        return "client/auth/register";
    }

    @PostMapping("/register")
    public String postRegister(@ModelAttribute("registerUser") @Valid RegisterDTO register,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "client/auth/register";
        }

        User user = this.userService.registerDTOUser(register);
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user.setRole(this.userService.getRoleByName("USER"));
        this.userService.handleSaveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        model.addAttribute("title", "Tuan Hiep Shop");
        return "client/auth/login";
    }

    @GetMapping("/access-deny")
    public String getDenyPage() {
        return "client/auth/deny";
    }

}
