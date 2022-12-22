package com.example.ecampus.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Enstitu {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long enstituID;
    public String enstituAdi;

    @OneToMany(cascade = CascadeType.ALL)
    List<Bolum> bolumListesi;
}
