package vn.hoidanit.laptopshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import vn.hoidanit.laptopshop.domain.Cart;
import vn.hoidanit.laptopshop.domain.CartDetail;
import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.domain.User;
import vn.hoidanit.laptopshop.repository.CartDetailRepository;
import vn.hoidanit.laptopshop.repository.CartRepository;
import vn.hoidanit.laptopshop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CartRepository cartRepository;
    private final CartDetailRepository cartDetailRepository;

    private final UserService userService;

    public ProductService(ProductRepository productRepository, CartRepository cartRepository,
            CartDetailRepository cartDetailRepository, UserService userService) {
        this.productRepository = productRepository;
        this.cartRepository = cartRepository;
        this.cartDetailRepository = cartDetailRepository;
        this.userService = userService;
    }

    public Product handleSaveProduct(Product product) {
        return this.productRepository.save(product);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Optional<Product> getProductId(long id) {
        return this.productRepository.findById(id);
    }

    public void getDeleteProduct(long id) {
        this.productRepository.deleteById(id);
    }

    public void addProductToCart(String email, long productId, HttpSession session) {
        /**
         * Lấy người dùng theo email
         * */
        User user = this.userService.getUserByEmail(email);

        if (user != null) {
            Cart cart = this.cartRepository.findByUser(user);
            // check xem có cart hay chưa, nếu chưa tạo mới
            if (cart == null) {
                // tạo mới cart
                Cart newCart = new Cart();
                newCart.setUser(user);
                newCart.setSum(0);

                cart = this.cartRepository.save(newCart);
            }

            // Theo product vào cart
            Optional<Product> productOptional = this.productRepository.findById(productId);
            if (productOptional.isPresent()) {
                // lấy sản pham
                Product realProduct = productOptional.get();

                // Check san pham da duoc them gio hang hay chua
                CartDetail oldDetail = this.cartDetailRepository.findByCartAndProduct(cart, realProduct);

                if (oldDetail == null) {
                    CartDetail cartDetail = new CartDetail();
                    cartDetail.setCart(cart);
                    cartDetail.setProduct(realProduct);
                    cartDetail.setPrice(realProduct.getPrice());
                    cartDetail.setQuantity(1);
                    this.cartDetailRepository.save(cartDetail);

                    int sum = cart.getSum() + 1;
                    cart.setSum(sum);
                    this.cartRepository.save(cart);
                    session.setAttribute("sum", sum);
                } else {
                    oldDetail.setQuantity(oldDetail.getQuantity() + 1);
                    this.cartDetailRepository.save(oldDetail);
                }

            }

        }
    }

    public Cart fetchByUser(User user) {
        return this.cartRepository.findByUser(user);
    }


    public void deleteProductFromCart(long productId, HttpSession session) {
        Optional<CartDetail> cartDetailOptional = this.cartDetailRepository.findById(productId);
        if (cartDetailOptional.isPresent()) {
            CartDetail cartDetail = cartDetailOptional.get();

            Cart currentCart = cartDetail.getCart();

            // delete cart-detail
            this.cartDetailRepository.delete(cartDetail);

            //update cart
            if (currentCart.getSum() > 1) {
                // update current cart
                int sum = currentCart.getSum() - 1;
                currentCart.setSum(sum);
                session.setAttribute("sum", sum);
                this.cartRepository.save(currentCart);
            }else {
                this.cartRepository.deleteById(currentCart.getId());
                session.setAttribute("sum", 0);

            }
        }

    }
}
