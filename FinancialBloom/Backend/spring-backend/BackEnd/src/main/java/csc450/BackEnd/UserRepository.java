package csc450.BackEnd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Optional custom query methods:
    User findByEmail(String email);
    User findByUsername(String username);

}
