package ru.ecomshop.userservice.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ecomshop.userservice.model.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
