package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CreateAccountController {

    @Autowired
    private CreateAccountService createAccountService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody CreateAccountRequest request) {
        String result = createAccountService.registerUser(request);
        return ResponseEntity.ok(result);
    }
}

