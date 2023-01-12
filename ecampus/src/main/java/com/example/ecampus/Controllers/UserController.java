package com.example.ecampus.Controllers;

import com.example.ecampus.Models.*;
import com.example.ecampus.Repos.UserRoleRepository;
import com.example.ecampus.Services.DersKayitService;
import com.example.ecampus.Services.SozlesmeService;
import com.example.ecampus.Services.UserRoleService;
import com.example.ecampus.Services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Slf4j
public class UserController {
    private final UserService userService;
    private final SozlesmeService sozlesmeService;
    private final UserRoleService userRoleService;
    private final UserRoleRepository userRoleRepository;
    private  final DersKayitService dersKayitService;
    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{id}/roles")
    public Collection<UserRole> getUsersAllRoles(@PathVariable Long id){

        return userService.getUsersRolesByUserId(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserForm> getUserwithId(@PathVariable Long id){
        User newuser = userService.getUserById(id);
        UserForm userForm = new UserForm(newuser.username, newuser.email, newuser.telno);
        return ResponseEntity.ok(userForm);
    }

    /*@GetMapping ("/ogrenciler")
    ResponseEntity<List<User>>
    public ResponseEntity<?> getAllOgrenciler()
    {
        return ResponseEntity.ok(userService.getAllOgrenci());
    }*/

    @GetMapping("/byRole/{rolename}")
    public ResponseEntity<?> getAllUserByRoleName(@PathVariable String  rolename){
        return ResponseEntity.ok(userService.getAllUserByRole(rolename));
    }

    @PostMapping()
    public ResponseEntity<UserForm> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        User new_user = userService.saveUser(user);
        System.out.println(new_user);
        UserForm userForm = new UserForm(new_user.username,new_user.email,new_user.telno);
        return ResponseEntity.created(uri).body(userForm);
    }


    /*@PostMapping("/{id}/roles")
    public ResponseEntity<User> addRoletoUser(@PathVariable Long id, @RequestBody String userRole){
        //User u = userService.getUserById(id);
        //u.getRoles().add(userRole);
        //userService.saveUser(u);

        return ResponseEntity.ok(userService.addRoleToUser(id,userRole));

    }*/

    @PostMapping("/{id}/roles")
    public ResponseEntity<?> addRoletoUser(@PathVariable Long id, @RequestBody UserRole userRole){
        User u = userService.getUserById(id);
        u.getRoles().add(userRole);
        userService.saveUser(u);

        return ResponseEntity.ok(u);

    }
    @Secured("ROLE_INSAN_KAYNAKLARI")
    @PostMapping("/personeller")
    public User personelEkleIK(@RequestBody User user)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());

        User new_user = user;

        //Collection<UserRole> roles = new_user.getRoles();
        //new_user.getRoles().removeAll(roles);
        new_user.getRoles().add(userRoleRepository.findByRoleID(6L));


        System.out.println(new_user);

        return userService.saveUser(new_user);
    }

    @Secured("ROLE_OGRENCI_ISLERI")
    @PostMapping("/ogrenciler")
    public User ogrenciEkleOI(@RequestBody User user)
    {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());

        User new_user = user;

        //Collection<UserRole> roles = new_user.getRoles();
        //new_user.getRoles().removeAll(roles);
        new_user.getRoles().add(userRoleRepository.findByRoleID(9L));

        System.out.println(new_user);

        return userService.saveUser(new_user);
    }

    @Secured("ROLE_INSAN_KAYNAKLARI")
    @PostMapping("/{id}/sozlesmeler")
    public ResponseEntity<?> addSozlesmetoUserIK(@PathVariable Long id,@RequestBody String title){
        userService.addSozlesmeToUserByID(id,title);
        return ResponseEntity.ok("Succesful !");
    }

    @GetMapping("/{id}/sozlesmeler")
    public List<Sozlesme> getSozlesmelerByUserId(@PathVariable Long id){
        return userService.getUsersSozlesmelerByUserId(id);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> updateUser(@PathVariable Long id, @RequestBody User newUser){
        return ResponseEntity.ok(userService.updateUser(id,newUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<User>> deleteUser(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

   /* @GetMapping("/{id}/derskayit")
    public ResponseEntity<List<DersKayit>> getUsersDerskayits(@PathVariable Long id){
        return ResponseEntity.ok(dersKayitService.findDersKayitByID(id));
    }*/
   /* @GetMapping("/ogrenci/{okulKimlikNo}/derskayitlari")
    public ResponseEntity<List<DersKayit>> getogrDerskayitlar(@PathVariable String okulKimlikNo){
        return ResponseEntity.ok(dersKayitService.findDersKayitToOgrenci());

    }*/

    @GetMapping("/{kimlikno}/derskayitlari")
    public ResponseEntity<List<DersKayit>> getDersKayitlariByKimlikNo(@PathVariable String kimlikno){
        return ResponseEntity.ok(dersKayitService.getAllDerskayitlaribykimliknoo(kimlikno));
    }


    @Data
    class UserRoleForm
    {
         String username;
         String roleName;
    }

    @Data
    class UserForm{
        public String username;
        public String email;
        public String telno;

        UserForm(String username,String email,String telno){
            this.username = username;
            this.email = email;
            this.telno = telno;
        }
    }
}
