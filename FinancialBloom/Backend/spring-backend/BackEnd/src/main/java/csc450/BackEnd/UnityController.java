package csc450.BackEnd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unity-data")
public class UnityController {

    @Autowired
    private UnityDataRepository unityDataRepository;

    @PostMapping
    public ResponseEntity<UnityDataRequest> saveUnityData(@RequestBody UnityDataRequest unityDataRequest) {
        UnityDataRequest savedData = unityDataRepository.save(unityDataRequest);
        return ResponseEntity.ok(savedData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnityDataRequest> getUnityData(@PathVariable Long id) {
        return unityDataRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}