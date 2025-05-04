/*
 * Author: Daniela Luna
 *
 * Description:
 * This repository interface provides database access methods for
 * BudgetEntry entities. It extends JpaRepository to inherit
 * built-in CRUD operations, and adds custom queries to fetch entries by user.
 *
 * Purpose:
 * Enables efficient interaction with the `budget_entries` table via
 * standard Spring Data JPA features, such as saving new entries and
 * retrieving a user's full budget history.
 */

package csc450.BackEnd;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BudgetEntryRepository extends JpaRepository<BudgetEntry, Integer> {

    List<BudgetEntry> findAllByUser(User user);

    // Optional: filter by type
    List<BudgetEntry> findAllByUserAndType(User user, String type);
}
