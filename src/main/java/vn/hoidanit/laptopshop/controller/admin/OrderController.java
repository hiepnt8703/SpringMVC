package vn.hoidanit.laptopshop.controller.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.service.OrderService;

import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/admin/order")
    public String getOrder(Model model, @RequestParam(value = "page") Optional<String> pageOptinal
    ) {
        int page = 1;
        try {
            if (pageOptinal.isPresent()){
                page = Integer.parseInt(pageOptinal.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int pageSize = 5;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Order> orderPage = this.orderService.fetchAllOrders(pageable);
        List<Order> orderList = orderPage.getContent();
        model.addAttribute("orders", orderList);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", orderPage.getTotalPages());
        model.addAttribute("pageSize", pageSize);
        return "admin/order/show";
    }

    @GetMapping("admin/order/{id}")
    public String getOrderDetailPage(@PathVariable long id, Model model) {
        Order order = this.orderService.fetchOrderById(id).get();
        model.addAttribute("order", order);
        model.addAttribute("id" , id);
        model.addAttribute("orderDetails", order.getOrderDetails());
        return "admin/order/detail";
    }

    @GetMapping("/admin/order/delete/{id}")
    public String getDeleteOrderPage (Model model , @PathVariable long id) {
        model.addAttribute("id" , id);
        model.addAttribute("newOrder" , new Order());
        return "admin/order/delete";
    }

    @PostMapping("/admin/order/delete")
    public String postDeleteOrder( @ModelAttribute("newOrder") Order order) {
        this.orderService.deleteOrderById(order.getId());
        return "redirect:/admin/order";
    }

    @GetMapping("/admin/order/update/{id}")
    public String getUpdateOrderPage(@PathVariable long id, Model model) {
        Optional<Order> order = this.orderService.fetchOrderById(id);
        model.addAttribute("newOrder", order.get());
        return "admin/order/update";
    }

    @PostMapping("/admin/order/update")
    public String handleUpdateOrder(@ModelAttribute("newOrder") Order order) {
        this.orderService.updateOrder(order);
        return "redirect:/admin/order";
    }
}
