package com.repos;

import com.domain.Usr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsrRepository extends JpaRepository<Usr, Integer> {
    Optional<Usr> findByUsername(String userName);              /* Want method which will search for Optional<Usr> with
                                                                * username field equaled to username argument.*/
}
