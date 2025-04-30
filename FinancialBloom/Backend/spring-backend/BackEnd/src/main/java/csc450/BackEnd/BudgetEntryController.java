/*
 * Author: Daniela Luna
 *
 * Description:
 * The BudgetEntryController exposes API endpoints to create and retrieve
 * budget entries associated with specific users. It maps client requests
 * to service-layer operations like saving and querying user-linked entries.
 *
 * Purpose:
 * Allows the frontend to send budget data (income, expenses, etc.) to be
 * stored in the database, and retrieve user-specific budget logs for display.
 *
 * Endpoints:
 * - POST /api/budget/add → adds a new budget entry
 * - GET /api/budget/user/{userId} → fetches all budget entries for a user
 */

package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/budget")
public class BudgetEntryController {

    @Autowired
    private BudgetEntryRepository budgetRepo;

    @Autowired
    private UserRepository userRepo;

    @PostMapping("/add")
    public ResponseEntity<?> addEntry(@RequestBody BudgetEntryDTO dto) {
        Optional<User> userOpt = userRepo.findById(dto.getUserId());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        BudgetEntry entry = new BudgetEntry();
        entry.setUser(userOpt.get());
        entry.setType(dto.getType());
        entry.setName(dto.getName());
        entry.setAmount(dto.getAmount());

        budgetRepo.save(entry);

        return ResponseEntity.ok("Budget entry saved successfully.");
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BudgetEntry>> getEntries(@PathVariable Integer userId) {
        Optional<User> userOpt = userRepo.findById(userId);

        if (userOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        List<BudgetEntry> entries = budgetRepo.findAllByUser(userOpt.get());
        return ResponseEntity.ok(entries);
    }
}
