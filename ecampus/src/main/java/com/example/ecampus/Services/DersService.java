package com.example.ecampus.Services;

import com.example.ecampus.Models.Ders;
import com.example.ecampus.Models.DersRole;
import com.example.ecampus.Repos.BolumRepository;
import com.example.ecampus.Repos.BolumRoleRepository;
import com.example.ecampus.Repos.DersRepository;
import com.example.ecampus.Repos.DersRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class DersService {
    private final DersRepository dersRepository;
    private final DersRoleRepository dersRoleRepository;

    public Ders getDersler(){
        return (Ders) dersRepository.findAll();
    }
    public Optional<Ders> getDerswithId(Long id){
        return dersRepository.findById(id);
    }
    public Ders getDerswithName(String dersAdi){
        return dersRepository.findBydersAdi(dersAdi);
    }
    public Ders addDers(Ders ders){
        return dersRepository.save(ders);
    }
    public Optional<Ders> updateDers(Long id, Ders newDers){
        return dersRepository.findById(id).map(ders -> {
           ders.setDersRole(newDers.getDersRole());
           ders.setDersAdi(newDers.getDersAdi());
           ders.setBolum(newDers.getBolum());
           return dersRepository.save(ders);
        });
    }
    public Optional<Ders> deleteDers(Long id){
        dersRepository.deleteById(id);
        var isRemoved = dersRepository.findById(id);
        return isRemoved;
    }
    public void addRoletoDers(String rolename,String dersAdi){
        log.info("{} dersi {} programı dersidir...",dersAdi,rolename);
        Ders ders = getDerswithName(dersAdi);
        DersRole dersRole = dersRoleRepository.findBydersRoleName(rolename);
        ders.setDersRole(dersRole);
    }


}
