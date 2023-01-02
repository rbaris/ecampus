package com.example.ecampus.Controllers;

import com.example.ecampus.Models.Ders;
import com.example.ecampus.Models.DersKayit;
import com.example.ecampus.Services.DersKayitService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/derskayitlari")
public class DersKayitController {
    private final DersKayitService dersKayitService;
    @GetMapping()
    public ResponseEntity<?> getderskayitlari(){
        return ResponseEntity.ok(dersKayitService.getDersKayitlari());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DersKayit>> getderskayitlari(@RequestBody Long id) throws ClassNotFoundException {
        return ResponseEntity.ok(dersKayitService.getDersKayit(id));
    }
    @PostMapping()
    public ResponseEntity<DersKayit> saveDerskaydi(@RequestBody DersKayit dersKayit){
        return ResponseEntity.ok(dersKayitService.addDersKayit(dersKayit));
    }
    @Secured("ROLE_OGRENCİ_İSLERİ")
    @GetMapping("/ogreniisleri/{id}")
    public ResponseEntity<Optional<DersKayit>> getDerskaydi(@RequestBody Long id) throws ClassNotFoundException {
        return ResponseEntity.ok(dersKayitService.getDersKayit(id));
    }

    @Secured("ROLE_OGRENCİ")
    @PutMapping("/ogrenci/{id}")
    public ResponseEntity<Optional<DersKayit>> kayitduzenleOgr(@PathVariable Long id,@RequestBody DersKayit dersKayit){

        return ResponseEntity.ok(dersKayitService.updateDersKayitOgr(id, dersKayit));
    }

    @Secured({"ROLE_OGRENCİ_İSLERİ","ROLE_ADMIN"})
    @PutMapping("/ogrenciisleri/{id}")
    public ResponseEntity<Optional<DersKayit>> kayitduzenleOgrIsleri(@PathVariable Long id,@RequestBody DersKayit dersKayit){

        return ResponseEntity.ok(dersKayitService.updateDersKayitOisleri(id, dersKayit));
    }

    @Secured({"ROLE_OGRENCİ_İSLERİ","ROLE_ADMIN"})
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<DersKayit>> silDersKayit(@PathVariable Long id)
    {
        return  ResponseEntity.ok(dersKayitService.deleteDersKayit(id));

    }

    @PostMapping("/{id}/ogrenci")
    public ResponseEntity<DersKayit> addOgrenciDersDersKayit(@PathVariable Long id,@RequestParam Long ogrenciID) throws ClassNotFoundException {
        return ResponseEntity.ok(dersKayitService.addKaydedilecekUsertoDersKayit(id,ogrenciID));
    }

    @PostMapping("/{id}/ogrenciIsleri")
    public ResponseEntity<DersKayit> addOgrenciIsleriDersDersKayit(@PathVariable Long id,@RequestParam Long OIsleri) throws ClassNotFoundException {
        return ResponseEntity.ok(dersKayitService.addOnaylayantoDersKayit(id,OIsleri));
    }
    @PostMapping("/{id}/ders")
    public ResponseEntity<Ders> addDersDersKayit(@PathVariable Long id, @RequestParam Long dersId) throws ClassNotFoundException {
        return ResponseEntity.ok(dersKayitService.addDerstoDersKayit(id,dersId));
    }

    ///Derskayitlari/3

/* ders kaydına onaylayan user ekleme
    derskaydına ders ekleme
    ders kaydına öğrenci ekleme
 */

}
