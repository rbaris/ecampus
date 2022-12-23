package com.example.ecampus.Services;

import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Models.Enstitu;
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
    public Optional<Enstitu> updateEnstitu(Long id, Enstitu newEnstitu){
        return enstituRepository.findById(id).map(enstitu -> {
            enstitu.setEnstituAdi(newEnstitu.getEnstituAdi());
            enstitu.setBolumListesi(newEnstitu.getBolumListesi());
            return enstituRepository.save(enstitu);
        });
    }
    public Optional<Enstitu> deleteEnstitu(Long id){
        enstituRepository.deleteById(id);
        var isRemoved = enstituRepository.findById(id);
        return isRemoved;
    }

}
