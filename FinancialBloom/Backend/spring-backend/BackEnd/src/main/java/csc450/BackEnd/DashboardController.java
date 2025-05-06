/**
 * Author: Daniela Luna
 * REST Controller for serving aggregated dashboard data.
 * Purpose: Handles GET requests to /api/dashboard-data/{userId} and returns a
 * combined response containing both budget entries and milestones for
 * that user.
 * Why separate from BudgetEntryController:
 * The BudgetEntryController focuses strictly on creating and retrieving
 * individual budget transactions. The dashboard needs combined, calculated,
 * and visualized data. Separating the dashboard logic into its own controller
 * keeps responsibilities clean and maintainable.</p>
 */
package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DashboardController {

    @Autowired
    private BudgetEntryRepository budgetRepo;

    @Autowired
    private MilestoneRepository milestoneRepo;

    @Autowired
    private UserRepository userRepo;

    @GetMapping("/dashboard-data/{userId}")
    public DashboardDataDTO getDashboardData(@PathVariable Integer userId) {
        Optional<User> userOpt = userRepo.findById(userId);
        if (userOpt.isEmpty()) {
            return new DashboardDataDTO(List.of(), List.of());
        }

        List<BudgetEntry> budgetEntries = budgetRepo.findAllByUser(userOpt.get());
        List<Milestone> milestone = milestoneRepo.findAllByUser(userOpt.get());

        return new DashboardDataDTO(budgetEntries, milestone);
    }
}
