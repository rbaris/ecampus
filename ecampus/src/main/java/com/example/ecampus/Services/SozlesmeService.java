package com.example.ecampus.Services;

import com.example.ecampus.Models.Sozlesme;
import com.example.ecampus.Repos.SozlesmeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class SozlesmeService {

    private final SozlesmeRepository sozlesmeRepository;

    public List<Sozlesme> getSozlesmeler()
    {
        log.info("Get Sozlesmeler...");
        return sozlesmeRepository.findAll();
    }

    public Sozlesme saveSozlesme(Sozlesme sozlesme)
    {
        log.info("Saving sozlesme...");
        sozlesmeRepository.save(sozlesme);
        return sozlesme;
    }

    public Sozlesme getSozlesme(Long id)
    {
        return sozlesmeRepository.findById(id).get();
    }



}
