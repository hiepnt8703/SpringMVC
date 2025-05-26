package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.domain.Order;
import vn.hoidanit.laptopshop.domain.User;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = """
    SELECT COUNT(*) AS total_orders FROM orders
""" , nativeQuery = true)
    long countOrders();

    List<Order> findByUser(User user);
}
