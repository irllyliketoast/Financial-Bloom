/**
 * Author: Daniela Luna
 * Repository interface for performing CRUD operations on Milestone entities.
 * Purpose: Allows Spring Data JPA to automatically generate queries for fetching
 * milestones from the database. The key method, findAllByUser(), enables
 * retrieval of user-specific milestone entries.</p>
 *
 * Why separate from BudgetEntryRepository:
 * Budget entries (income/expenses) are short-term and frequent, while
 * milestones are goal-oriented and long-term. Separating them ensures
 * cleaner queries and better alignment with domain logic.</p>
 */

package csc450.BackEnd;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MilestoneRepository extends JpaRepository<Milestone, Long> {
    List<Milestone> findAllByUser(User user);
}
