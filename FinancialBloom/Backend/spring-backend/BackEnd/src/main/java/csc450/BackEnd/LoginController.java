package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/login")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        User user = userRepository.findByEmailAndPassword(email, password);
        if (user != null) {
            return ResponseEntity.ok("Login successful! Welcome, " + user.getFName() + " " + user.getLName());
        } else {
            return ResponseEntity.status(401).body("Invalid credentials. Please try again.");
        }
    }
}
