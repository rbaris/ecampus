package com.example.ecampus.Repos;

import com.example.ecampus.Models.BolumRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BolumRoleRepository extends JpaRepository<BolumRole,Long> {

    BolumRole findBybolumRoleAdi(String rolename);

}
