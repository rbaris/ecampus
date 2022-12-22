package com.example.ecampus.Services;

import com.example.ecampus.Models.UserRole;
import com.example.ecampus.Repos.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
