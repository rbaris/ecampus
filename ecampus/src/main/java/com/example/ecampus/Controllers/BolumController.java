package com.example.ecampus.Controllers;
import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Models.BolumRole;
import com.example.ecampus.Models.Ders;
import com.example.ecampus.Services.BolumRoleService;
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
    private final BolumRoleService bolumRoleService;

    @GetMapping()
    public ResponseEntity<?> getBolumler(){
        return ResponseEntity.ok(bolumService.bolumList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Bolum> getBolum(@PathVariable Long id){
        return ResponseEntity.ok(bolumService.getBolum(id));
    }
    @PostMapping()
    public ResponseEntity<Bolum> addBolum(@RequestBody Bolum bolum){
        return ResponseEntity.ok(bolumService.addBolum(bolum));
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Bolum>> updateBolum(@PathVariable Long id,@RequestBody Bolum bolum){
        return ResponseEntity.ok(bolumService.updateBolum(id, bolum));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Bolum>> silBolum(@PathVariable Long id){
        return ResponseEntity.ok(bolumService.deleteBolum(id));
    }
    @PostMapping("/{id}/role")
    public ResponseEntity<?> addRoletoBolum(@PathVariable Long id, @RequestBody BolumRole bolumRole){
        Bolum bolum = bolumService.getBolum(id);
        bolum.setBolumRole(bolumRole);
        bolumService.addBolum(bolum);
        return ResponseEntity.ok(bolum);

    }

    @PostMapping("/{id}/ders")
    public ResponseEntity<?> addDerstoBolum(@PathVariable Long id, @RequestBody Ders ders){
        Bolum bolum = bolumService.getBolum(id);
        bolum.getDersListesi().add(ders);
        bolumService.addBolum(bolum);
        return ResponseEntity.ok(bolum);


    }

}
