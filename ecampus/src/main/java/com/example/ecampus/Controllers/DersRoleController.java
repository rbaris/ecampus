package com.example.ecampus.Controllers;

import com.example.ecampus.Models.Ders;
import com.example.ecampus.Models.DersRole;
import com.example.ecampus.Services.DersRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/dersroles")
public class DersRoleController {
    private final DersRoleService dersRoleService;

    @GetMapping()
    public ResponseEntity<?> getDersroles(){
        return ResponseEntity.ok(dersRoleService.getdersRoleList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<DersRole>> getDersrole(@RequestBody Long id){
        return ResponseEntity.ok(dersRoleService.getDersrolewithid(id));
    }
    @PostMapping()
    public ResponseEntity<DersRole> savedersrole(@RequestBody DersRole dersRole){
        return ResponseEntity.ok(dersRoleService.saveDersRole(dersRole));
    }
}
