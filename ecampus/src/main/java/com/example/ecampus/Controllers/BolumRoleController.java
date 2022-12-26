package com.example.ecampus.Controllers;

import com.example.ecampus.Models.BolumRole;
import com.example.ecampus.Services.BolumRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bolumroles")
public class BolumRoleController {
    private final BolumRoleService bolumRoleService;

    @GetMapping()
    public ResponseEntity<?> getBolumroles(){
        return ResponseEntity.ok(bolumRoleService.getAllBolumRoles());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<BolumRole>> getBolumrole(@RequestBody Long id){
        return ResponseEntity.ok(bolumRoleService.getRolewithId(id));
    }
    @PostMapping()
    public ResponseEntity<BolumRole> addBolumRole(@RequestBody BolumRole bolumRole){
        return ResponseEntity.ok(bolumRoleService.savebolumRole(bolumRole));
    }
}
