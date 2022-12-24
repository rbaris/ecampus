package com.example.ecampus.Controllers;

import com.example.ecampus.Models.Enstitu;
import com.example.ecampus.Services.BolumService;
import com.example.ecampus.Services.EnstituService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/enstituler")
public class EnstituController {
    private final EnstituService enstituService;
    private final BolumService bolumService;

    @GetMapping()
    public ResponseEntity<?> getAllEnstiuler(){
        return  ResponseEntity.ok(enstituService.getAllEnstitu());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Enstitu>> getEnstitu(@RequestBody Long id){
        return ResponseEntity.ok(enstituService.getEnstitubyId(id));
    }
    @PostMapping()
    public ResponseEntity<Enstitu> saveEnstitu(@RequestBody Enstitu enstitu){
        return ResponseEntity.ok(enstituService.addEnstitu(enstitu));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Enstitu>> updateEnstitu(@RequestBody Long id, Enstitu newEnstitu){
        return ResponseEntity.ok(enstituService.updateEnstitu(id, newEnstitu));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Enstitu>> deleteEnstitu(@RequestBody Long id){
        return ResponseEntity.ok(enstituService.deleteEnstitu(id));
    }
}
