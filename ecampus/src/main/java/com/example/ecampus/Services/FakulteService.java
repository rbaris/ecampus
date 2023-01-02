package com.example.ecampus.Services;

import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Models.Fakulte;
import com.example.ecampus.Repos.BolumRepository;
import com.example.ecampus.Repos.FakulteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
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
    public List<Bolum> getBolumByFakulteId(Long id)
    {
        Optional<Fakulte> fakulte=fakulteRepository.findById(id);
        return fakulte.orElseThrow().getBolumListesi();
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
    public void addBolumtoFakulte(String bolumAdi,Long id){
        log.info("{} bölümü {} fakülteye eklendi...", bolumAdi,id);
        Optional<Fakulte> fakulte = getFakulte(id);
        Bolum bolum = bolumRepository.findBybolumAdi(bolumAdi);
        if(bolum.toString().equals("Fakülte"))fakulte.orElseThrow().getBolumListesi().add(bolum);
        else new Exception("bölüm fakülteye eklenemedi.");
    }
    public Optional<Fakulte> updateFakulte(Long id, Fakulte newFakulte){
        return fakulteRepository.findById(id)
                .map(fakulte -> {fakulte.setFakulteAdi(newFakulte.getFakulteAdi());
                fakulte.setBolumListesi(newFakulte.getBolumListesi());
                return fakulteRepository.save(fakulte);
                });
    }
    public Optional<Fakulte> deleteFakulte(Long id){
        var isRemoved = fakulteRepository.findById(id);
        fakulteRepository.deleteById(id);
        return isRemoved;
    }

    public Optional<Fakulte> deleteFakulteByBolumId(Long id,Long silinenId)
    {

        Optional<Fakulte> fakulte=fakulteRepository.findById(id);
        Optional <Bolum> bolum =bolumRepository.findById(silinenId);
        fakulte.orElseThrow().getBolumListesi().remove(bolum);
        return fakulte;

    }
}
