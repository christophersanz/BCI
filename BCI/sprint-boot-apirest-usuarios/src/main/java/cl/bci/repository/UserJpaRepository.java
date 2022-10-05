package cl.bci.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import cl.bci.model.User;

public interface UserJpaRepository extends JpaRepository<User, Long> {

}
