/**
 * Author: Daniela Luna
 * MilestoneController
 *
 * This controller handles API requests related to user savings milestones.
 * It allows the frontend to add new milestone entries that are tied to a specific user.
 *
 * Purpose:
 * - Receive POST requests to save a user's milestone (e.g. amount saved toward a goal).
 * - Acts independently from the DashboardDataController, which only retrieves and aggregates data.
 *
 * Endpoints:
 * - POST /api/milestones/add → Saves a milestone for a given user
 *
 * This controller exists to handle write operations for milestones, keeping separation of concerns
 * between data modification (here) and data retrieval/aggregation (DashboardDataController).
 */
package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/milestones")
public class MilestoneController {

    @Autowired
    private MilestoneRepository milestoneRepo;

    @Autowired
    private UserRepository userRepo;

    /**
     * Handles saving a new milestone for a user.
     * POST /api/milestones/add
     */
    @PostMapping("/add")
    public ResponseEntity<?> addMilestone(@RequestBody MilestoneDTO dto) {
        Optional<User> userOpt = userRepo.findById(dto.getUserId());

        if (userOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Milestone milestone = new Milestone();
        milestone.setName(dto.getName());
        milestone.setAmountSaved(dto.getAmountSaved());
        milestone.setGoalAmount(dto.getGoalAmount());
        milestone.setUser(userOpt.get());

        milestoneRepo.save(milestone);
        return ResponseEntity.ok("Milestone saved successfully");
    }
}
