package com.example.ecampus.Services;

import com.example.ecampus.Models.Ders;
import com.example.ecampus.Models.DersKayit;
import com.example.ecampus.Models.User;
import com.example.ecampus.Repos.DersKayitRepository;
import com.example.ecampus.Repos.DersRepository;
import com.example.ecampus.Repos.UserRepository;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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

    public List<DersKayit> getDersKayitlari(){
        return dersKayitRepository.findAll();
    }
    public Optional<DersKayit> getDersKayit(Long id) throws ClassNotFoundException {
        Optional<DersKayit> dersKayit = dersKayitRepository.findById(id);
        if(dersKayit == null) throw new ClassNotFoundException("Ders kaydı bulunamadı...");

        return Optional.of(dersKayit.get());
        //return dersKayitRepository.findById(id);
    }

    public Ders addDerstoDersKayit(Long dersKayitID,Long id) throws ClassNotFoundException {

        Optional<DersKayit> dersKayit = getDersKayit(dersKayitID);
        Ders ders = dersService.getDersById(id);
        if(dersKayit == null) throw new ClassNotFoundException("Ders kaydı bulunamadı...");

        dersKayit.get().kaydedilecekDers=ders;
        return ders;

    }
    public DersKayit addOnaylayantoDersKayit(Long dersKayitID,Long id) throws ClassNotFoundException {
        DersKayit dersKayit = dersKayitRepository.findBydersKayitID(dersKayitID);
        User onaylayanUser= userService.getUserById(id);
        if(onaylayanUser.getRoles().toString().equals("ROLE_OGRENCI_ISLERI")) {
            dersKayit.onaylayanUser = onaylayanUser;
            dersKayit.setDersKayitDurum(Boolean.TRUE);
            dersKayitRepository.save(dersKayit);
            return dersKayit;
        }
        else new Exception("işlem geçersiz ders onaylanmadı.");
        return null;
    }
    public DersKayit addKaydedilecekUsertoDersKayit(Long dersKayitID,Long id) throws ClassNotFoundException {
        DersKayit dersKayit = dersKayitRepository.findBydersKayitID(dersKayitID);
        User kaydedilecekUser = userService.getUserById(id);
        if(kaydedilecekUser.getRoles().toString().equals("ROLE_OGRENCI"))
        {
            dersKayit.dersinOgrencisi = kaydedilecekUser;
            dersKayitRepository.save(dersKayit);
            return dersKayit;
        }
        else new Exception("işlem geçersiz öğrenci atanamadı.");
        return null;
    }

    public Optional<DersKayit> updateDersKayitOgr(Long id, DersKayit newDersKayit)
    {
       return dersKayitRepository.findById(id)
               .map(dersKayit -> {
                   dersKayit.setKaydedilecekDers(newDersKayit.getKaydedilecekDers());
                   return dersKayitRepository.save(dersKayit);
               });
    }

    public Optional<DersKayit> updateDersKayitOisleri(Long id, DersKayit newDersKayit){
        return dersKayitRepository.findById(id)
                .map(dersKayit -> {
                    dersKayit.setKaydedilecekDers(newDersKayit.getKaydedilecekDers());
                    dersKayit.setOnaylayanUser(newDersKayit.getOnaylayanUser());
                    dersKayit.setDersinOgrencisi(newDersKayit.getDersinOgrencisi());
                    return dersKayitRepository.save(dersKayit);
                });
    }

    public Optional<DersKayit> deleteDersKayit(Long id){
        var isRemoved = dersKayitRepository.findById(id);
        dersKayitRepository.deleteById(id);
        return isRemoved;
    }
    public DersKayit addDersKayit(DersKayit dersKayit){
        return dersKayitRepository.save(dersKayit);
    }

    public List<DersKayit> findDersKayitByID(Long id)
    {
        return dersKayitRepository.findAllByDersinOgrencisi_userID(id);
    }


    public List<DersKayit> findDersKayitToOgrenci(Long id)
    {
     return dersKayitRepository.findAllByDersinOgrencisi_userID(id);
    }
}
