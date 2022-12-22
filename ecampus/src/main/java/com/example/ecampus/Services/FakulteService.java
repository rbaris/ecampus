package com.example.ecampus.Services;

import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Models.Fakulte;
import com.example.ecampus.Repos.BolumRepository;
import com.example.ecampus.Repos.FakulteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FakulteService {
    private final FakulteRepository fakulteRepository;
    private final BolumRepository bolumRepository;

    /*FakulteService(FakulteRepository fakulteRepository,BolumRepository bolumRepository){
        this.fakulteRepository = fakulteRepository;
        this.bolumRepository = bolumRepository;
    }*/

    public List<Fakulte> getAllFakulteler(){
        log.info("Get All fakulteler...");
        return fakulteRepository.findAll();
    }
    public Fakulte addFakulte(Fakulte fakulte){
        return fakulteRepository.save(fakulte);
    }
    public Optional<Fakulte> getFakulte(Long id){
        return fakulteRepository.findById(id);
    }
    public Fakulte getFakultewithName(String fakulteName){
        return fakulteRepository.findByfakulteAdi(fakulteName);
    }
    public void addBolumtoFakulte(String bolumAdi,String fakulteAdi){
        log.info("{} bölümü {} fakülteye eklendi...", bolumAdi,fakulteAdi);
        Fakulte fakulte = getFakultewithName(fakulteAdi);
        Bolum bolum = bolumRepository.findBybolumAdi(bolumAdi);
        fakulte.getBolumListesi().add(bolum);
    }
    public Optional<Fakulte> updateFakulte(Long id, Fakulte newFakulte){
        return fakulteRepository.findById(id)
                .map(fakulte -> {fakulte.setFakulteAdi(newFakulte.getFakulteAdi());
                fakulte.setBolumListesi(newFakulte.getBolumListesi());
                return fakulteRepository.save(fakulte);
                });
    }
    public Optional<Fakulte> deleteFakulte(Long id){
        fakulteRepository.deleteById(id);
        var isRemoved = fakulteRepository.findById(id);
        return isRemoved;
    }

}
