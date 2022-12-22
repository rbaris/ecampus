package com.example.ecampus.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class BolumRole {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long bolumRoleID;
    public String bolumRoleAdi;
}
