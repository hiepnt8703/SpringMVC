package vn.hoidanit.laptopshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.hoidanit.laptopshop.domain.User;

// crud : them doc sua xoa
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User nguoidung);

    void deleteById(Long id);

    User getById(Long id);

    boolean existsByEmail(String email);
    User findUserByEmail(String email);
}
