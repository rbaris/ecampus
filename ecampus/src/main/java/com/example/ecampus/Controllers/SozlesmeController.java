package com.example.ecampus.Controllers;

import com.example.ecampus.Models.Sozlesme;
import com.example.ecampus.Services.SozlesmeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/sozlesmeler")
public class SozlesmeController {
    private final SozlesmeService sozlesmeService;

    @GetMapping()
    public ResponseEntity<?> getAllSozlesmeler(){
        return ResponseEntity.ok(sozlesmeService.getSozlesmeler());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Sozlesme> getSozlesmewithID(@PathVariable Long id){
        return ResponseEntity.ok(sozlesmeService.getSozlesme(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Sozlesme>> updateSozlesme(@PathVariable Long id, Sozlesme newSozlesme){
        return ResponseEntity.ok(sozlesmeService.updateSozlesme(id, newSozlesme));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Sozlesme>> deleteSozlesme(@PathVariable Long id){
        return ResponseEntity.ok(sozlesmeService.deleteSozlesme(id));
    }
    @PostMapping()
    public ResponseEntity<Sozlesme> addSozlesme(@RequestBody Sozlesme sozlesme){
        return ResponseEntity.ok(sozlesmeService.saveSozlesme(sozlesme));
    }

}
