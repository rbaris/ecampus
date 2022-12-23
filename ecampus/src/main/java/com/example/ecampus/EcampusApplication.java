package com.example.ecampus;

import com.example.ecampus.Models.Sozlesme;
import com.example.ecampus.Models.User;
import com.example.ecampus.Models.UserRole;
import com.example.ecampus.Services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class EcampusApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcampusApplication.class, args);
    }


    @Bean
    BCryptPasswordEncoder passwordEncoder(){
        return  new BCryptPasswordEncoder();
    }


    @Bean
    CommandLineRunner run(UserService userService) {
        return args ->
        {
            userService.saveRole(new UserRole(null, "ROLE_USER"));
            userService.saveRole(new UserRole(null, "ROLE_MANAGER"));
            userService.saveRole(new UserRole(null, "ROLE_ADMIN"));
            userService.saveSozlesme(new Sozlesme(null,"İş Sağlığı ve Güvenliği","11.03.2025"));
            userService.saveSozlesme(new Sozlesme(null,"Çalışma İzni","20.05.2026")) ;

            userService.saveUser(new User(null,"baris","baris@email.com","12345","02668356801",new Sozlesme(),"",new ArrayList<>()));

    //delete metotunda sorun olabilir.
            userService.addSozlesmeToUser("baris","Çalışma İzni");
            userService.addRoleToUser("baris", "ROLE_USER");
            userService.addRoleToUser("baris", "ROLE_ADMIN");

        };
    }
}
