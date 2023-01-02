package com.example.ecampus.Repos;

import com.example.ecampus.Models.DersKayit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DersKayitRepository extends JpaRepository<DersKayit,Long> {
    List<DersKayit> findAllByDersinOgrencisi_userID(Long id);
    DersKayit findBydersKayitID(Long id);
    DersKayit findByDersinOgrencisi_UserID(Long id);
}
