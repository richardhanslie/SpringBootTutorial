package id.co.bca.spring.helloworld.repository;

import id.co.bca.spring.helloworld.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
