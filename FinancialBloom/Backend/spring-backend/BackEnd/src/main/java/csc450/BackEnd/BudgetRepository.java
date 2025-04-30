package csc450.BackEnd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer> {

    // Optional custom queries:
    Budget findByName(String name);

    // You can add more like:
    // List<Budget> findByUserId(int userId); — if you later link budgets to specific users
}
