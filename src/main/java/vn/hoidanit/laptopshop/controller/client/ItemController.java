package vn.hoidanit.laptopshop.controller.client;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
public class ItemController {

    private final ProductService productService;

    public ItemController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public String getProductDetail(Model model, @PathVariable long id) {
        Optional<Product> product = this.productService.getProductId(id);
        model.addAttribute("product", product.get());
        model.addAttribute("id", id);
        return "client/product/detail";
    }

    @PostMapping("/add-product-to-cart/{id}")
    public String addProductToCart(@PathVariable long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        long productId = id;
        String email = (String) session.getAttribute("email");

        this.productService.addProductToCart(email , productId , session);

        return "redirect:/";
    }

    @GetMapping("/cart")
    public String getCartPage(Model model , HttpServletRequest request) {
        String title = "Laptop Shop - Cart";
        model.addAttribute("title", title);
        User currentUser = new User();
        Cart cart = this.productService.fetchByUser(currentUser);
        
        List<CartDetail> cartDetails = cart.getCartDetails();

        model.addAttribute("cartDetails", cartDetails);

        return "client/cart/show";
    }
    

}
