package com.example.ecampus.Services;

import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Models.Enstitu;
import com.example.ecampus.Models.Fakulte;
import com.example.ecampus.Repos.BolumRepository;
import com.example.ecampus.Repos.EnstituRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class EnstituService {
    private final EnstituRepository enstituRepository;
    private final BolumRepository bolumRepository;

    public Enstitu addEnstitu(Enstitu enstitu){
        enstituRepository.save(enstitu);
        return enstitu;
    }
    public List<Enstitu> getAllEnstitu(){
        return enstituRepository.findAll();
    }
    public Optional<Enstitu> getEnstitubyId(Long id){
        return enstituRepository.findById(id);
    }
    public Enstitu getEnstituwithName(String name){
        return enstituRepository.findByenstituAdi(name);
    }
    public void addBolumtoEnstitu(String bolumadi,String enstituAdi){
        Enstitu enstitu = getEnstituwithName(enstituAdi);
        Bolum bolum = bolumRepository.findBybolumAdi(bolumadi);
        enstitu.getBolumListesi().add(bolum);
    }
    public void addBolumtoEnsitu(String bolumAdi,String enstituAdi)throws Exception{
        log.info("{} bölümü {} fakülteye eklendi...", bolumAdi,enstituAdi);
        Enstitu enstitu = getEnstituwithName(enstituAdi);
        Bolum bolum = bolumRepository.findBybolumAdi(bolumAdi);
        if(bolum.toString().equals("Ensitü")) enstitu.getBolumListesi().add(bolum);
        else new Exception("bolum enstituye eklenemedi.");
    }
    public Optional<Enstitu> updateEnstitu(Long id, Enstitu newEnstitu){
        return enstituRepository.findById(id).map(enstitu -> {
            enstitu.setEnstituAdi(newEnstitu.getEnstituAdi());
            enstitu.setBolumListesi(newEnstitu.getBolumListesi());
            return enstituRepository.save(enstitu);
        });
    }
    public Optional<Enstitu> deleteEnstitu(Long id){
        var isRemoved = enstituRepository.findById(id);
        enstituRepository.deleteById(id);
        return isRemoved;
    }


    public List<Bolum> getBolumByEnstituId(Long id)
    {
        Optional<Enstitu> enstitu=enstituRepository.findById(id);
        return enstitu.orElseThrow().getBolumListesi();
    }
    public void addBolumtoEnstitu(String bolumAdi,Long id){
        log.info("{} bölümü {} fakülteye eklendi...", bolumAdi,id);
        Optional<Enstitu> enstitu = getEnstitubyId(id);
        Bolum bolum = bolumRepository.findBybolumAdi(bolumAdi);
        if(bolum.toString().equals("Fakülte"))enstitu.orElseThrow().getBolumListesi().add(bolum);
        else new Exception("bölüm fakülteye eklenemedi.");
    }
    public Optional<Enstitu> deleteEnstituByBolumId(Long id,Long silinenId)
    {

        Optional<Enstitu> enstitu=enstituRepository.findById(id);
        Optional <Bolum> bolum =bolumRepository.findById(silinenId);
        enstitu.orElseThrow().getBolumListesi().remove(bolum);
        return enstitu;

    }
}
