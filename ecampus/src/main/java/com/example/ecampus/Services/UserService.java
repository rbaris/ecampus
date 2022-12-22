package com.example.ecampus.Services;

import com.example.ecampus.Models.Sozlesme;
import com.example.ecampus.Models.User;
import com.example.ecampus.Models.UserRole;
import com.example.ecampus.Repos.SozlesmeRepository;
import com.example.ecampus.Repos.UserRepository;
import com.example.ecampus.Repos.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Service

@RequiredArgsConstructor
@Transactional
@Slf4j //simple logging api
public class UserService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserRoleRepository userRoleRepository;
    @Autowired
    private final SozlesmeRepository sozlesmeRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public List<User> getAllUsers()
    {
        log.info("Get All Users...");
        return userRepository.findAll();
    }
    public User saveUser(User user)
    {
        log.info("Saving user...");
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }
    public User getUser(String username)
    {
        return userRepository.findByUsername(username);
    }
    public UserRole saveRole(UserRole role)
    {
        log.info("Saving role...");
        userRoleRepository.save(role);
        return role;
    }

    public void  addRoleToUser(String username, String roleName)
    {
        log.info("Adding {} role to user {}...", roleName,username);
        User user = getUser(username);
        UserRole role = userRoleRepository.findByroleName(roleName);
        user.getRoles().add(role);

    }

    public Sozlesme saveSozlesme(Sozlesme sozlesme)
    {
        log.info("Saving sozlesme...");
        sozlesmeRepository.save(sozlesme);
        return sozlesme;
    }

    public void  addSozlesmeToUser(String username, String sozlesmeTitle)
    {
        log.info("Adding {} sozlesme to user {}...", sozlesmeTitle,username);
        User user = getUser(username);
        Sozlesme sozlesme = sozlesmeRepository.findBytitle(sozlesmeTitle);
        user.sozlesme = sozlesme;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("Username not found!");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.roleName)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user == null)
            throw new UsernameNotFoundException("Username not found!");
        return user.get();
    }
}
