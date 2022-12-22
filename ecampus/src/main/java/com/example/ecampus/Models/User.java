package com.example.ecampus.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long userID;
    public String username;
    public String email;
    public String password;
    public String telno;
    @OneToOne(cascade = CascadeType.ALL)
    public Sozlesme sozlesme;
    public String okulKimlikNo="";


    @ManyToMany(fetch = FetchType.EAGER)
    Collection<UserRole> roles = new ArrayList<>();
}
