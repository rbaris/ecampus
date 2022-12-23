package com.example.ecampus.Services;

import com.example.ecampus.Models.BolumRole;
import com.example.ecampus.Repos.BolumRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class BolumRoleService {
    BolumRoleRepository bolumRoleRepository;

    public BolumRole saveBolumRole(BolumRole bolumRole){
        return bolumRoleRepository.save(bolumRole);
    }
    public List<BolumRole> getAllBolumRoles(){
        return bolumRoleRepository.findAll();
    }

    public Optional<BolumRole> getRolewithId(Long id){
        return bolumRoleRepository.findById(id);
    }
    public Optional<BolumRole> deleteBolumRole(Long id){
        bolumRoleRepository.deleteById(id);
        var isRemoved = bolumRoleRepository.findById(id);
        return isRemoved;
    }
    public Optional<BolumRole> updateBolumRole(Long id, BolumRole newBolumRole){
        return bolumRoleRepository.findById(id).map(bolumRole -> {
           bolumRole.setBolumRoleAdi(newBolumRole.getBolumRoleAdi());
           return bolumRoleRepository.save(bolumRole);
        });
    }
}
