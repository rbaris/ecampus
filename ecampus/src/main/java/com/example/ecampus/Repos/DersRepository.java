package com.example.ecampus.Repos;

import com.example.ecampus.Models.Ders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DersRepository extends JpaRepository<Ders,Long> {

    Ders findBydersAdi(String dersname);
    Ders findBydersID(Long id);

}
