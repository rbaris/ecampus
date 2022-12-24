package com.example.ecampus.Controllers;

import com.example.ecampus.Models.Ders;
import com.example.ecampus.Services.DersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dersler")
public class DersController {
    private final DersService dersService;

    @GetMapping()
    public ResponseEntity<?> getAlldersler(){
        return ResponseEntity.ok(dersService.getDersler());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Ders>> getDers(@RequestBody Long id){
        return ResponseEntity.ok(dersService.getDerswithId(id));
    }
    @PostMapping()
    public ResponseEntity<Ders> addDers(@RequestBody Ders ders){
        return ResponseEntity.ok(dersService.addDers(ders));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Ders>> guncelleDers(@RequestBody Long id, Ders newDers){
        return ResponseEntity.ok(dersService.updateDers(id, newDers));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Ders>> silDers(@RequestBody Long id){
        return ResponseEntity.ok(dersService.deleteDers(id));
    }

}
