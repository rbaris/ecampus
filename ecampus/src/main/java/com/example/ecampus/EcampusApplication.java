package com.example.ecampus;

import com.example.ecampus.Models.*;
import com.example.ecampus.Services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@Slf4j
public class EcampusApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcampusApplication.class, args);
        //System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));
    }


    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }



    @Bean
    CommandLineRunner run( UserService userService, DersRoleService dersRoleService,DersService dersService,BolumRoleService bolumRoleService,BolumService bolumService,FakulteService fakulteService,EnstituService enstituService) {
        return args ->
        {
            bolumService.addBolum(new Bolum(null,"Bilgisayar Mühendisliği",new ArrayList<>(),new BolumRole()));
            bolumService.addBolum(new Bolum(null,"Yapay Zeka Lisanüstü Programı",new ArrayList<>(),new BolumRole()));
            bolumRoleService.savebolumRole(new BolumRole(null,"Fakülte"));
            bolumRoleService.savebolumRole(new BolumRole(null,"Enstitü"));
            enstituService.addEnstitu(new Enstitu(null,"Mühendslik Enstitüsü",new ArrayList<>()));
            fakulteService.addFakulte(new Fakulte(null,"Mühendislik Fakültesi",new ArrayList<>()));
            dersRoleService.saveDersRole(new DersRole(null,"Lisans"));
            dersRoleService.saveDersRole(new DersRole(null,"Yüksek Lisans"));
            dersService.addDers(new Ders(null,"Matematik 1",new Bolum(),new DersRole()));
            dersService.addDers(new Ders(null,"Görüntü İşleme",new Bolum(),new DersRole()));
            userService.saveRole(new UserRole(null, "ROLE_USER"));
            userService.saveRole(new UserRole(null, "ROLE_ADMIN"));
            userService.saveRole(new UserRole(null,"ROLE_AKADEMISYEN"));
            userService.saveRole(new UserRole(null,"ROLE_FAKULTE_YONETICI"));
            userService.saveRole(new UserRole(null,"ROLE_ENSTITU_YONETICI"));
            userService.saveRole(new UserRole(null,"ROLE_PERSONEL"));
            userService.saveRole(new UserRole(null,"ROLE_INSAN_KAYNAKLARI"));
            userService.saveRole(new UserRole(null,"ROLE_SİSTEM_YONETICISI"));
            userService.saveRole(new UserRole(null,"ROLE_OGRENCI"));
            userService.saveRole(new UserRole(null,"ROLE_OGRENCI_ISLERI"));
            userService.saveSozlesme(new Sozlesme(null,"İş Sağlığı ve Güvenliği","11.03.2025"));
            userService.saveSozlesme(new Sozlesme(null,"Çalışma İzni","20.05.2026")) ;

            userService.saveUser(new User(null,"baris","baris@email.com","12345","0531346235",new ArrayList<>(),"21345431",new ArrayList<>()));

            userService.addSozlesmeToUser("baris","Çalışma İzni");
            userService.addRoleToUser("baris", "ROLE_USER");
            userService.addRoleToUser("baris", "ROLE_ADMIN");
            dersService.addRoletoDers("Lisans","Matematik 1");
            dersService.addRoletoDers("Yüksek Lisans","Görüntü İşleme");
            bolumService.addRoletoBolum("Fakülte","Bilgisayar Mühendisliği");
            bolumService.addRoletoBolum("Enstitü","Yapay Zeka Lisansüstü Programı");
            enstituService.addBolumtoEnstitu("Yapay Zeka Lisansüstü Programı","Mühendslik Enstitüsü");
            fakulteService.addBolumtoFakulte("Bilgisayar Mühendisliği","Mühendislik Fakültesi");

        };
    }
}
