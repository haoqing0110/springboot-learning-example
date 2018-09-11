package spring.boot.core.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User Persistent layer API
 *
 * Created by bysocket on 21/07/2017.
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
