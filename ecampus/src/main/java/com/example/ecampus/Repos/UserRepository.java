package com.example.ecampus.Repos;

import com.example.ecampus.Models.User;
import com.example.ecampus.Models.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.management.relation.Role;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    User findByUserID(Long id);
    User findByusername(String username);
    List<User> findAllByRoles_RoleName(String rolename);
    User findByOkulKimlikNo(String okulKimlikNo);

    List<User> findAllByRoles(Role role);
}
