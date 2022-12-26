package com.example.ecampus.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Ders {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long dersID;
    public String dersAdi;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "bolumID")
    public Bolum bolum;

    @OneToOne
    public DersRole dersRole;
}
