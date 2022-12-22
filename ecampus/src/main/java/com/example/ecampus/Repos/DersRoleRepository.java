package com.example.ecampus.Repos;

import com.example.ecampus.Models.DersRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DersRoleRepository extends JpaRepository<DersRole,Long> {
}
