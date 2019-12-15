package io.repos;

import io.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsrRepository extends JpaRepository<User, Long> {     // usr table repository.
    User findByUsername(String username);               // Finding User by username value.
}
