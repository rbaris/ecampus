package com.example.ecampus.Repos;

import com.example.ecampus.Models.Bolum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BolumRepository extends JpaRepository<Bolum,Long> {

}
