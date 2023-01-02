package com.example.ecampus.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class DersKayit {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long dersKayitID;
    @ManyToOne
    @JoinColumn(name = "OnayalayanID")
    public User onaylayanUser;
    @ManyToOne
    @JoinColumn(name = "OgrenciID")
    public User dersinOgrencisi;
    @ManyToOne
    @JoinColumn(name = "DersID")
    public Ders kaydedilecekDers;

    public Boolean dersKayitDurum;

}
