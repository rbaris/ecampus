package com.example.ecampus.DTOs;

import com.example.ecampus.Models.Sozlesme;
import com.example.ecampus.Models.UserRole;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class UserDTO {

    public String username;
    public String email;
    public String telno;

}
