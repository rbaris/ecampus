package com.example.ecampus.Controllers;

import com.example.ecampus.Models.DersKayit;
import com.example.ecampus.Services.DersKayitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/derskayitlari")
public class DersKayitController {
    private final DersKayitService dersKayitService;
    @Secured("ROLE_OGRENCİ")
    @GetMapping()
    public ResponseEntity<?> getderskayitlari(){
        return ResponseEntity.ok(dersKayitService.getDersKayitlari());
    }
    @PostMapping()
    public ResponseEntity<DersKayit> saveDerskaydi(@RequestBody DersKayit dersKayit){
        return ResponseEntity.ok(dersKayitService.addDersKaydi(dersKayit));
    }
    @Secured("ROLE_OGRENCİ_İSLERİ")
    @GetMapping("/{id}")
    public ResponseEntity<DersKayit> getDerskaydi(@RequestBody Long id) throws ClassNotFoundException {
        return ResponseEntity.ok(dersKayitService.getDersKaydi(id));
    }
/* ders kaydına onaylayan user ekleme
    derskaydına ders ekleme
    ders kaydına öğrenci ekleme
 */

}
