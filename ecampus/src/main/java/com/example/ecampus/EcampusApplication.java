package com.example.ecampus;

import com.example.ecampus.Models.*;
import com.example.ecampus.Services.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;

@SpringBootApplication
@Slf4j
public class EcampusApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(EcampusApplication.class, args);
        System.out.println(Arrays.asList(applicationContext.getBeanDefinitionNames()));

    }


    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


//HATA 2
    @Bean
    CommandLineRunner run( UserService userService, DersRoleService dersRoleService,DersService dersService,BolumRoleService bolumRoleService,BolumService bolumService,FakulteService fakulteService,EnstituService enstituService) {
        return args ->
        {
            userService.saveRole(new UserRole(1L, "ROLE_USER"));
            userService.saveRole(new UserRole(2L, "ROLE_ADMIN"));
            userService.saveRole(new UserRole(3L,"ROLE_AKADEMISYEN"));
            userService.saveRole(new UserRole(4L,"ROLE_FAKULTE_YONETICI"));
            userService.saveRole(new UserRole(5L,"ROLE_ENSTITU_YONETICI"));
            userService.saveRole(new UserRole(6L,"ROLE_PERSONEL"));
            userService.saveRole(new UserRole(7L,"ROLE_INSAN_KAYNAKLARI"));
            userService.saveRole(new UserRole(8L,"ROLE_SISTEM_YONETICISI"));
            userService.saveRole(new UserRole(9L,"ROLE_OGRENCI"));
            userService.saveRole(new UserRole(10L,"ROLE_OGRENCI_ISLERI"));
            userService.saveSozlesme(new Sozlesme(null,"İş Sağlığı ve Güvenliği","11.03.2025"));
            userService.saveSozlesme(new Sozlesme(null,"Çalışma İzni","20.05.2026")) ;
            dersRoleService.saveDersRole(new DersRole(null,"Lisans"));
            dersRoleService.saveDersRole(new DersRole(null,"Yüksek Lisans"));

            bolumRoleService.saveBolumRole(new BolumRole(null,"Fakülte"));
            bolumRoleService.saveBolumRole(new BolumRole(null,"Enstitü"));
            bolumService.addBolum(new Bolum(null,"Bilgisayar Mühendisliği",null,null));
            bolumService.addBolum(new Bolum(null,"Yapay Zeka Lisanüstü Programı",null,null));
            dersService.addDers(new Ders(null,"Matematik 1",null));
            dersService.addDers(new Ders(null,"Görüntü İşleme",null));


            /*           bolumRoleService.savebolumRole(new BolumRole(null,"Fakülte"));
            bolumRoleService.savebolumRole(new BolumRole(null,"Enstitü"));
            bolumService.addBolum(new Bolum(null,"Bilgisayar Mühendisliği",new ArrayList<>(),new BolumRole()));
            bolumService.addBolum(new Bolum(null,"Yapay Zeka Lisanüstü Programı",new ArrayList<>(),new BolumRole()));*/







            /*enstituService.addEnstitu(new Enstitu(null,"Mühendslik Enstitüsü",new ArrayList<>()));
            fakulteService.addFakulte(new Fakulte(null,"Mühendislik Fakültesi",new ArrayList<>()));*/



            userService.saveUser(new User(null,"melih","melih@email.com","12345","0531346235","20-10-2000","20-10-2020",null,new ArrayList<>(),"21345431",new ArrayList<>()));

            userService.saveUser(new User(null,"rafet","rafet@email.com","12345","0531346235","20-10-2000","20-10-2020",null,new ArrayList<>(),"21345431",new ArrayList<>()));
            userService.saveUser(new User(null,"fatih","fatih@email.com","12345","1","21-5-2000","21-2-2020",null,new ArrayList<>(),"1",new ArrayList<>()));
            userService.saveUser(new User(null,"taha","taha@email.com","12345","2","28-2-2000","28-2-2020",null,new ArrayList<>(),"2",new ArrayList<>()));

            userService.addSozlesmeToUser(1L,"Çalışma İzni");
            userService.addRoleToUser("rafet", "ROLE_USER");
            userService.addRoleToUser("rafet", "ROLE_ADMIN");
            userService.addRoleToUser("rafet", "ROLE_INSAN_KAYNAKLARI");
            userService.addRoleToUser("melih", "ROLE_ADMIN");
            userService.addRoleToUser("melih", "ROLE_OGRENCI_ISLERI");



            userService.addRoleToUser("fatih", "ROLE_OGRENCI");
            userService.addRoleToUser("fatih", "ROLE_PERSONEL");
            userService.addRoleToUser("taha", "ROLE_OGRENCI");

           /* dersService.addRoletoDers("Lisans","Matematik 1");
            dersService.addRoletoDers("Yüksek Lisans","Görüntü İşleme");
            bolumService.addRoletoBolum("Fakülte","Bilgisayar Mühendisliği");
            bolumService.addRoletoBolum("Enstitü","Yapay Zeka Lisansüstü Programı");
            enstituService.addBolumtoEnstitu("Yapay Zeka Lisansüstü Programı","Mühendslik Enstitüsü");
            fakulteService.addBolumtoFakulte("Bilgisayar Mühendisliği","Mühendislik Fakültesi");*/

        };
    }
}
