package com.example.ecampus.Services;

import com.example.ecampus.Models.Ders;
import com.example.ecampus.Models.DersKayit;
import com.example.ecampus.Models.User;
import com.example.ecampus.Repos.DersKayitRepository;
import com.example.ecampus.Repos.DersRepository;
import com.example.ecampus.Repos.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class DersKayitService {
    private final DersRepository dersRepository;
    private final UserRepository userRepository;

    private final DersService dersService;
    private final UserService userService;
    private final DersKayitRepository dersKayitRepository;

    public DersKayit getDersKayitlari(){
        return (DersKayit) dersKayitRepository.findAll();
    }
    public DersKayit getDersKaydi(Long id) throws ClassNotFoundException {
        Optional<DersKayit> dersKayit = dersKayitRepository.findById(id);
        if(dersKayit == null) throw new ClassNotFoundException("Ders kaydı bulunamadı...");
        return dersKayit.get();
        //return dersKayitRepository.findById(id);
    }
    public void addDerstoDersKaydi(Long dersKayitID,String dersAdi) throws ClassNotFoundException {

        DersKayit dersKayit = getDersKaydi(dersKayitID);
        Ders ders = dersService.getDerswithName(dersAdi);
        dersKayit.kaydedilecekDers = ders;

    }
    public void addOnaylayantoDersKaydi(Long dersKayitID,String username) throws ClassNotFoundException {
        DersKayit dersKayit = getDersKaydi(dersKayitID);
        User onaylayanUser= userService.getUser(username);
        if(onaylayanUser.getRoles().toString().equals("ROLE_OGRENCI_ISLERI"))
            dersKayit.onaylayanUser = onaylayanUser;
        else new Exception("işlem geçersiz ders onaylanmadı.");
    }
    public void addKaydedilecekUsertoDersKaydi(Long dersKayitID,String username) throws ClassNotFoundException {
        DersKayit dersKayit = getDersKaydi(dersKayitID);
        User kaydedilecekUser = userService.getUser(username);
        if(kaydedilecekUser.getRoles().toString().equals("ROLE_OGRENCI")) dersKayit.dersinOgrencisi = kaydedilecekUser;
        else new Exception("işlem geçersiz öğrenci atanamadı.");
    }

    public Optional<DersKayit> updateDersKayit(Long id, DersKayit newDersKayit){
       return dersKayitRepository.findById(id)
               .map(dersKayit -> {
                   dersKayit.setKaydedilecekDers(newDersKayit.getKaydedilecekDers());
                   dersKayit.setOnaylayanUser(newDersKayit.getOnaylayanUser());
                   dersKayit.setDersinOgrencisi(newDersKayit.getDersinOgrencisi());
                   return dersKayitRepository.save(dersKayit);
               });
    }

    public Optional<DersKayit> updateDersKaydi(Long id){
        var isRemoved = dersKayitRepository.findById(id);
        dersKayitRepository.deleteById(id);
        return isRemoved;
    }
    public DersKayit addDersKaydi(DersKayit dersKayit){
        return dersKayitRepository.save(dersKayit);
    }
}
