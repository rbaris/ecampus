package com.example.ecampus.Repos;

import com.example.ecampus.Models.DersKayit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DersKayitRepository extends JpaRepository<DersKayit,Long> {

}
