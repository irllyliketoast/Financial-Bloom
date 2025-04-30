package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;


@RestController
@RequestMapping("/api")
public class CreateAccountController {

    private final CreateAccountService service;

    @Autowired
    public CreateAccountController(CreateAccountService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody CreateAccountRequest request) {
        String resultMessage = service.registerUser(request);
        return ResponseEntity.ok(Collections.singletonMap("message", resultMessage));
    }
}


