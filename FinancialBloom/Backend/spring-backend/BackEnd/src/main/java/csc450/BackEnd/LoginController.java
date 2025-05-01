package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.Base64;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String rawPassword = loginRequest.getPassword();

        System.out.println("LoginRequest payload: username=" + loginRequest.getUsername() + ", password=" + loginRequest.getPassword());
        ;
        System.out.println("Raw input password: " + rawPassword);
        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("User not found.");
            return ResponseEntity.status(401).build();
        }

        boolean passwordMatch = verifyPassword(rawPassword, user.getPassword());
        System.out.println("Password match: " + passwordMatch);

        if (!passwordMatch) {
            return ResponseEntity.status(401).build();
        }
        Map<String, Object> response = Map.of(
            "success", true,
            "UserID", user.getUserID()
        );
        return ResponseEntity.ok(response);
    }

    // Make sure this matches how you hashed it in CreateAccountService
    private boolean verifyPassword(String inputPassword, String storedHash) {
        try {
            String[] parts = storedHash.split(":");
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] expectedHash = Base64.getDecoder().decode(parts[1]);

            PBEKeySpec spec = new PBEKeySpec(inputPassword.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] actualHash = factory.generateSecret(spec).getEncoded();

            return java.util.Arrays.equals(expectedHash, actualHash);
        } catch (Exception e) {
            return false;
        }
    }
}
