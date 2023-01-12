package com.example.ecampus.Controllers;

import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Models.Fakulte;
import com.example.ecampus.Services.BolumService;
import com.example.ecampus.Services.FakulteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;
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
    public ResponseEntity<Optional<Fakulte>> getFakulte(@PathVariable Long id){
        return ResponseEntity.ok(fakulteService.getFakulte(id));
    }
    @PostMapping()
    public ResponseEntity<Fakulte> addFakulte(@RequestBody Fakulte fakulte){
        return ResponseEntity.ok(fakulteService.addFakulte(fakulte));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<Fakulte>> deleteFakulte(@PathVariable Long id){
        return ResponseEntity.ok(fakulteService.deleteFakulte(id));
    }
    @PutMapping("{id}")
    public ResponseEntity<Optional<Fakulte>> updateFakulte(@PathVariable Long id,@RequestBody Fakulte newFakulte){
        return ResponseEntity.ok(fakulteService.updateFakulte(id, newFakulte));
    }

    @GetMapping("/{id}/bolumler")
    public ResponseEntity<List<Bolum>> getBolumByFakulte(@PathVariable Long id){
        return ResponseEntity.ok(fakulteService.getBolumByFakulteId(id));
    }
    @PostMapping("/{id}/bolumler")
    public ResponseEntity <Fakulte> addBolumByFakulte(@PathVariable Long id,@RequestBody Bolum bolum)
    {
        Fakulte fakulte=fakulteService.getFakulteO(id);
        fakulte.getBolumListesi().add(bolum);
        fakulteService.addFakulte(fakulte);
        return ResponseEntity.ok(fakulte);


        /*fakulteService.addBolumtoFakulte(bolumAdi,id);
        return ResponseEntity.ok(bolumAdi);*/
    }

    @DeleteMapping("/{ida}/bolumler/{idb}")
    public ResponseEntity<List<Bolum>> deleteBolumByFakulte(@PathVariable("ida") Long ida, @PathVariable("idb") Long idb)
    {
        fakulteService.deleteFakulteByBolumId(ida,idb);

        return ResponseEntity.ok(fakulteService.getBolumByFakulteId(ida));

    }
}
