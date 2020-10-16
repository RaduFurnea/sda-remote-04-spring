package ro.sda.spring.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.sda.spring.thymeleaf.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}