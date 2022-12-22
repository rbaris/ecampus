package com.example.ecampus.Repos;

import com.example.ecampus.Models.Sozlesme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SozlesmeRepository extends JpaRepository<Sozlesme,Long> {

    Sozlesme findBytitle(String sozlesmeTitle);
}
