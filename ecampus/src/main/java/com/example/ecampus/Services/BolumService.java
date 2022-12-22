package com.example.ecampus.Services;

import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Models.Ders;
import com.example.ecampus.Repos.BolumRepository;
import com.example.ecampus.Repos.DersRepository;
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
public class BolumService {

    private final BolumRepository bolumRepository;
    private final DersRepository dersRepository;

    public Bolum addBolum(Bolum bolum){
        return bolumRepository.save(bolum);
    }

    public List<Bolum> bolumList(){
        return bolumRepository.findAll();
    }

    public Optional<Bolum> getBolum(Long id){
        return bolumRepository.findById(id);
    }

    public Bolum getBolumwithName(String bolumAdi){
        return bolumRepository.findBybolumAdi(bolumAdi);
    }

    public Optional<Bolum> updateBolum(Long id, Bolum newBolum){
        return bolumRepository.findById(id)
                .map(bolum -> { bolum.setBolumAdi(newBolum.getBolumAdi());
                    bolum.setBolumRole(newBolum.getBolumRole());
                    bolum.setDersListesi(newBolum.getDersListesi());
                    return bolumRepository.save(bolum);
                });
    }

    public Optional<Bolum> deleteBolum(Long id){
        bolumRepository.deleteById(id);
        var isRemoved = bolumRepository.findById(id);
        return isRemoved;
    }

    public void addDerstoBolum(String dersname,String bolumName){

        log.info("{} dersi {} bölüme eklendi...", dersname,bolumName);
        Bolum bolum = getBolumwithName(bolumName);
        Ders ders = dersRepository.findBydersAdi(dersname);
        bolum.getDersListesi().add(ders);

    }

}
