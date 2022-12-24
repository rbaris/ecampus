package com.example.ecampus.Services;

import com.example.ecampus.Models.DersRole;
import com.example.ecampus.Repos.DersRoleRepository;
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
public class DersRoleService {

    private final DersRoleRepository dersRoleRepository;

    public DersRole saveDersRole(DersRole dersRole){
        dersRoleRepository.save(dersRole);
        return dersRole;
    }
    public Optional<DersRole> getDersrolewithid(Long id){
        return dersRoleRepository.findById(id);
    }
    public List<DersRole> getdersRoleList(){
        return dersRoleRepository.findAll();
    }
    public Optional<DersRole> deleteDersRole(Long id){
        dersRoleRepository.deleteById(id);
        var isRemoved = dersRoleRepository.findById(id);
        return isRemoved;
    }
    public Optional<DersRole> updateDersRole(Long id, DersRole newDersRole){
        return dersRoleRepository.findById(id)
                .map(dersRole -> {dersRole.setDersRoleName(newDersRole.getDersRoleName());
                return dersRoleRepository.save(dersRole);
                });
    }
}
