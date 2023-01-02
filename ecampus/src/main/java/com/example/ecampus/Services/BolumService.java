package com.example.ecampus.Services;

import com.example.ecampus.Models.Bolum;
import com.example.ecampus.Models.BolumRole;
import com.example.ecampus.Models.Ders;
import com.example.ecampus.Models.User;
import com.example.ecampus.Repos.BolumRepository;
import com.example.ecampus.Repos.BolumRoleRepository;
import com.example.ecampus.Repos.DersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final BolumRoleRepository bolumRoleRepository;

    public Bolum addBolum(Bolum bolum){
        return bolumRepository.save(bolum);
    }

    public List<Bolum> bolumList(){
        return bolumRepository.findAll();
    }

    public Bolum getBolum(Long id){
       Optional<Bolum> bolum = bolumRepository.findById(id);
        if(bolum == null)
            throw new UsernameNotFoundException("Username not found!");
        return bolum.get();
        //return bolumRepository.findById(id);
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

        var isRemoved = bolumRepository.findById(id);
        bolumRepository.deleteById(id);
        return isRemoved;
    }

    public void addDerstoBolum(String dersname,String bolumName){

        log.info("{} dersi {} bölüme eklendi...", dersname,bolumName);
        Bolum bolum = getBolumwithName(bolumName);
        Ders ders = dersRepository.findBydersAdi(dersname);
        if(ders.dersRole.dersRoleName.equals("Lisans") && bolum.getBolumRole().bolumRoleAdi.equals("Fakülte")) bolum.getDersListesi().add(ders);
        else if(ders.dersRole.dersRoleName.equals("Yüksek Lisans") && bolum.getBolumRole().bolumRoleAdi.equals("Enstitü")) bolum.getDersListesi().add(ders);
        else new Exception("ders eklenemedi.");
    }

    public void addRoletoBolum(String rolename,String bolumname){
        log.info("{} dersi {} programı dersidir...",bolumname,rolename);
        Bolum bolum = getBolumwithName(bolumname);
        BolumRole bolumRole = bolumRoleRepository.findBybolumRoleAdi(rolename);
        bolum.setBolumRole(bolumRole);
    }

}
