package com.example.ecampus.Controllers;
import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Services.BolumService;
import com.example.ecampus.Services.DersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bolumler")
public class BolumController {
    private final BolumService bolumService;
    private final DersService dersService;

    @GetMapping()
    public ResponseEntity<?> getBolumler(){
        return ResponseEntity.ok(bolumService.bolumList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Bolum>> getBolum(@RequestBody Long id){
        return ResponseEntity.ok(bolumService.getBolum(id));
    }
    @PostMapping()
    public ResponseEntity<Bolum> addBolum(@RequestBody Bolum bolum){
        return ResponseEntity.ok(bolumService.addBolum(bolum));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Bolum>> updateBolum(@RequestBody Long id, Bolum bolum){
        return ResponseEntity.ok(bolumService.updateBolum(id, bolum));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Bolum>> silBolum(@RequestBody Long id){
        return ResponseEntity.ok(bolumService.deleteBolum(id));
    }

}
