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
public class Fakulte {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long fakulteID;
    public String fakulteAdi;

    @OneToMany(cascade = CascadeType.ALL)
    List<Bolum> bolumListesi;
}
