package com.example.ecampus.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userID;
    public String username;
    public String email;
    public String password;
    public String telno;
    @OneToMany(cascade = CascadeType.ALL)
    public List<Sozlesme> sozlesmeList;
    public String okulKimlikNo="";


    @ManyToMany(fetch = FetchType.EAGER)
    Collection<UserRole> roles = new ArrayList<>();

  /*  public User forDTO(String username,String email,String telno){
        this.userID = null;
        this.username = username;
        this.email = email;
        this.telno = telno;
        this.sozlesmeList = new ArrayList<>();
        this.roles = new ArrayList<>();
    }
    public User  passwordRequired(String username,String password,String email,String telno){
        this.userID = null;
        this.username = username;
        this.password = password;
        this.email = email;
        this.telno = telno;
        this.sozlesmeList = new ArrayList<>();
        this.roles = new ArrayList<>();
    }*/

}
