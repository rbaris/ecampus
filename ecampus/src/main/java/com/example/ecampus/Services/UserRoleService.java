package com.example.ecampus.Services;

import com.example.ecampus.Models.UserRole;
import com.example.ecampus.Repos.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserRoleService {
    private final UserRoleRepository userRoleRepository;

    public UserRole saveRole(UserRole role)
    {
        userRoleRepository.save(role);
        return  role;
    }

    public List<UserRole> getAll()
    {

        return userRoleRepository.findAll();
    }
    public Optional<UserRole> deleteRole(Long id){
        userRoleRepository.deleteById(id);
        var isRemoved = userRoleRepository.findById(id);
        return isRemoved;
    }

    public Optional<UserRole> updateRole(Long id, UserRole newUserRole){
        return userRoleRepository.findById(id)
                .map(userRole -> {userRole.setRoleName(newUserRole.getRoleName());
                return userRoleRepository.save(userRole);
        });
    }
}
