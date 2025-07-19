package vn.hoidanit.laptopshop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import vn.hoidanit.laptopshop.domain.Product;
import vn.hoidanit.laptopshop.service.ProductService;
import vn.hoidanit.laptopshop.service.UploadService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;

@Controller
public class ProductController {
    private final UploadService uploadService;
    private final ProductService productService;

    public ProductController(UploadService uploadService, ProductService productService) {
        this.uploadService = uploadService;
        this.productService = productService;
    }

    @GetMapping("/admin/product")
    public String getHomeProduct(Model model,
        @RequestParam(value = "page" , defaultValue = "1") int page
    ) {
        String abc = "Tuan Hiep shop";
        // phan trang
        Pageable pageable = PageRequest.of(page - 1, 2);
        Page<Product> prs = this.productService.fetchProducts(pageable);
        List<Product> listProducts = prs.getContent();
        model.addAttribute("products", listProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", prs.getTotalPages());
        return "admin/product/index";
    }

    @GetMapping("/admin/product/create")
    public String getCreate(Model model) {
        model.addAttribute("newProduct", new Product());
        return "admin/product/create";
    }

    @PostMapping("/admin/product/create")
    public String getProduct(Model model,
            @ModelAttribute("newProduct") @Valid Product product,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile file) {

        // validation
        if (bindingResult.hasErrors()) {
            return "/admin/product/create";
        }
        // end validation
        String avatar = this.uploadService.handleUploadSave(file, "product");
        product.setImage(avatar);
        this.productService.handleSaveProduct(product);
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/{id}")
    public String getProductDetail(Model model, @PathVariable long id) {
        Optional<Product> product = this.productService.getProductId(id);
        model.addAttribute("product", product.get());
        return "admin/product/detail";
    }

    @GetMapping("/admin/product/edit/{id}")
    public String getEdit(Model model, @PathVariable long id) {
        Optional<Product> product = this.productService.getProductId(id);
        model.addAttribute("newProduct", product.get());
        return "admin/product/edit";
    }

    @PostMapping("/admin/product/update")
    public String postProductUpdate(@ModelAttribute("newProduct") @Valid Product product,
            BindingResult bindingResult,
            @RequestParam("imageFile") MultipartFile file) {

        // validation
        if (bindingResult.hasErrors()) {
            return "admin/product/edit";
        }
        // end
        Product productCurrent = this.productService.getProductId(product.getId()).get();
        // upload product
        if (productCurrent != null) {
            if (!file.isEmpty()) {
                String img = this.uploadService.handleUploadSave(file, "product");
                productCurrent.setImage(img);
            }
            productCurrent.setName(product.getName());
            productCurrent.setPrice(product.getPrice());
            productCurrent.setDetailDesc(product.getDetailDesc());
            productCurrent.setShortDesc(product.getShortDesc());
            productCurrent.setQuantity(product.getQuantity());
            productCurrent.setFactory(product.getFactory());
            productCurrent.setTarget(product.getTarget());

            this.productService.handleSaveProduct(productCurrent);
        }
        return "redirect:/admin/product";
    }

    @GetMapping("/admin/product/delete/{id}")
    public String getDeleteProduct(Model model, @PathVariable long id) {
        Product product = this.productService.getProductId(id).get();
        model.addAttribute("id", id);
        model.addAttribute("newProduct", product);
        return "admin/product/delete";
    }

    @PostMapping("/admin/product/delete")
    public String postDeleteProduct(Model model, @ModelAttribute("newProduct") Product product) {
        this.productService.getDeleteProduct(product.getId());
        return "redirect:/admin/product";
    }

}
