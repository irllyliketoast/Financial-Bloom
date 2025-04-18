package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Base64;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@Service
public class CreateAccountService {

    @Autowired
    private UserRepository userRepository;

    public String registerUser(CreateAccountRequest request) {
        if (!request.getFName().matches("[a-zA-Z]+")) return "Invalid first name.";
        if (!request.getLName().matches("[a-zA-Z]+")) return "Invalid last name.";
        if (!isValidEmail(request.getEmail())) return "Invalid email format.";
        if (userRepository.findByEmail(request.getEmail()) != null) return "Email already exists.";
        if (!isStrongPassword(request.getPassword())) return "Weak password.";

        String hashedPassword = hashPassword(request.getPassword());
        String dateCreated = LocalDate.now().toString();

        User newUser = new User(
                request.getUsername(),
                request.getFName(),
                request.getLName(),
                request.getEmail(),
                hashedPassword,
                dateCreated
        );

        userRepository.save(newUser);

        return "Account created successfully.";
    }

    private boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$");
    }

    private boolean isStrongPassword(String password) {
        return password != null &&
                password.length() >= 7 &&
                password.matches(".*[A-Z].*") &&
                password.matches(".*[@!\\-.$].*");
    }

    private String hashPassword(String password) {
        try {
            byte[] salt = new byte[16];
            SecureRandom sr = new SecureRandom();
            sr.nextBytes(salt);

            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = factory.generateSecret(spec).getEncoded();

            return Base64.getEncoder().encodeToString(salt) + ":" + Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Password hashing failed", e);
        }
    }
}
