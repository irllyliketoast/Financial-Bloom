package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    @PostMapping("/create")
    public ResponseEntity<String> createBudget(@RequestBody BudgetDTO budgetDTO) {
        Budget newBudget = new Budget(
                budgetDTO.getName(),
                budgetDTO.getIncomeStreams(),
                budgetDTO.getExpenseStreams()
        );

        budgetRepository.save(newBudget);
        return ResponseEntity.ok("Budget created successfully!");
    }

    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable int id) {
        return budgetRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBudget(@PathVariable int id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return ResponseEntity.ok("Budget deleted.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
