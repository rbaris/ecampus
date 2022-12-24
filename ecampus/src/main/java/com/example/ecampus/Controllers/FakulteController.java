package com.example.ecampus.Controllers;

import com.example.ecampus.Models.Fakulte;
import com.example.ecampus.Services.BolumService;
import com.example.ecampus.Services.FakulteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/fakulteler")
public class FakulteController {
    private final FakulteService fakulteService;
    private final BolumService bolumService;

    @GetMapping()
    public ResponseEntity<?> getallFakulteler(){
        return ResponseEntity.ok(fakulteService.getAllFakulteler());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Fakulte>> getFakulte(@RequestBody Long id){
        return ResponseEntity.ok(fakulteService.getFakulte(id));
    }
    @PostMapping()
    public ResponseEntity<Fakulte> addFakulte(@RequestBody Fakulte fakulte){
        return ResponseEntity.ok(fakulteService.addFakulte(fakulte));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Fakulte>> deleteFakulte(@RequestBody Long id){
        return ResponseEntity.ok(fakulteService.deleteFakulte(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<Optional<Fakulte>> updateFakulte(@RequestBody Long id, Fakulte newFakulte){
        return ResponseEntity.ok(fakulteService.updateFakulte(id, newFakulte));
    }

}
