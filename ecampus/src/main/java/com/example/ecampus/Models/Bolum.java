package com.example.ecampus.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Bolum {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bolumID;
    public String bolumAdi;
    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    List<Ders> dersListesi;
    @OneToOne
    BolumRole bolumRole;

    public String toString(){
        return this.bolumRole.bolumRoleAdi;
    }
}
