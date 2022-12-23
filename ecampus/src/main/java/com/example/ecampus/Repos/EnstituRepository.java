package com.example.ecampus.Repos;

import com.example.ecampus.Models.Enstitu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnstituRepository extends JpaRepository<Enstitu,Long> {

    Enstitu findByenstituAdi(String name);
}
