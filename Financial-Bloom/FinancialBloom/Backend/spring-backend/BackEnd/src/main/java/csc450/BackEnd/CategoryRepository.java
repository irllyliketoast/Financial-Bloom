package csc450.BackEnd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    // Optional: Add custom queries later like:
    // List<Category> findByNameContaining(String keyword);
}
