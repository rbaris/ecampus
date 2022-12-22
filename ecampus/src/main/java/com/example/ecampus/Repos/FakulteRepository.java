package com.example.ecampus.Repos;

import com.example.ecampus.Models.Fakulte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FakulteRepository extends JpaRepository<Fakulte,Long> {

}
