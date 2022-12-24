package com.example.ecampus.Controllers;

import com.example.ecampus.Models.User;
import com.example.ecampus.Services.SozlesmeService;
import com.example.ecampus.Services.UserRoleService;
import com.example.ecampus.Services.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final SozlesmeService sozlesmeService;
    private final UserRoleService userRoleService;

    @GetMapping()
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/users").toUriString());
        User new_user = userService.saveUser(user);
        System.out.println(new_user);
        return ResponseEntity.created(uri).body(new_user);
    }

    @PostMapping("/roles")
    public ResponseEntity<?> addRoletoUser(@RequestBody UserRoleForm userRoleForm){
        userService.addRoleToUser(userRoleForm.getUsername(), userRoleForm.getRoleName());
        return ResponseEntity.ok("Succesful !");
    }

    @PostMapping("/sozlesmeler")
    public ResponseEntity<?> addSozlesmetoUser(@RequestBody String username,String title){
        userService.addSozlesmeToUser(username,title);
        return ResponseEntity.ok("Succesful !");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Optional<User>> updateUser(@RequestBody Long id, User newUser){
        return ResponseEntity.ok(userService.updateUser(id,newUser));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<User>> deleteUser(@RequestBody Long id){
        return ResponseEntity.ok(userService.deleteUser(id));
    }

    @Data
    class UserRoleForm
    {
        private String username;
        private String roleName;
    }
}
