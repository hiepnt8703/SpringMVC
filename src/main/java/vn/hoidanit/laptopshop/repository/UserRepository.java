package vn.hoidanit.laptopshop.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.domain.User;

// crud : them doc sua xoa
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User nguoidung);

    List<User> findByEmail(String email);
}
