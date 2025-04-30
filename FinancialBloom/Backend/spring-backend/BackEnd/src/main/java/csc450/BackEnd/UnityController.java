package csc450;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import csc450.BackEnd.UnityDataRequest;



@RestController
@RequestMapping("/unity")
@CrossOrigin(origins = "*") // enable CORS for Unity WebGL
public class UnityController {

    @PostMapping("/post-score")
    public ResponseEntity<String> receiveUnityData(@RequestBody UnityDataRequest data) {
        System.out.println("Received from Unity: " + data.getPlayerPosition() + ", " + data.getSeedsCollected() 
            + data.getGrid() + ", " + data.getInventorySeeds());

        // Optional: Save to DB or file
        return ResponseEntity.ok("Data received");
    }
}
