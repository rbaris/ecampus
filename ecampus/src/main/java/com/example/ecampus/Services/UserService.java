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
        return userRepository.findByusername(username);
    }

    public User getUserByOkulKimlikNo(String okulKimlikNo){return userRepository.findByOkulKimlikNo(okulKimlikNo);}
    public List<User> getAllUserByRole(String rolename)
    {
        return userRepository.findAllByRoles_RoleName(rolename);

    }


    public UserRole saveRole(UserRole role)
    {
        log.info("Saving role...");
        userRoleRepository.save(role);
        return role;
    }

    public void addRoleToUser(String username, String roleName)
    {
        log.info("Adding {} role to user {}...", roleName,username);
        User user = getUser(username);
        UserRole role = userRoleRepository.findByroleName(roleName);
        user.getRoles().add(role);

    }

    public User addRoleToUser(Long userId, String roleName)
    {
        User user = userRepository.findByUserID(userId);
        UserRole role = userRoleRepository.findByroleName(roleName);
        user.getRoles().add(role);
        userRepository.save(user);
        log.info("Adding {} role to user {}...", roleName,userId);

        return user;

    }


    public Sozlesme saveSozlesme(Sozlesme sozlesme)
    {
        log.info("Saving sozlesme...");
        sozlesmeRepository.save(sozlesme);
        return sozlesme;
    }

    public void  addSozlesmeToUser(Long id, String sozlesmeTitle)
    {
        log.info("Adding {} sozlesme to user {}...", sozlesmeTitle,id);
        User user = getUserById(id);
        Sozlesme sozlesme = sozlesmeRepository.findBytitle(sozlesmeTitle);
        user.sozlesmeList.add(sozlesme);
    }
    public void  addSozlesmeToUserByID(Long id, String sozlesmeTitle)
    {
        log.info("Adding {} sozlesme to user {}...", sozlesmeTitle,id);
        User user = getUserById(id);
        Sozlesme sozlesme = sozlesmeRepository.findBytitle(sozlesmeTitle);
        user.sozlesmeList.add(sozlesme);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByusername(username);
        if(user == null)
            throw new UsernameNotFoundException("Username not found!");
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.roleName)));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);
    }

    public User getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if(user == null)
            throw new UsernameNotFoundException("Username not found!");
        return user.get();
    }

    public Optional<User> deleteUser(Long id){
        var isRemoved = userRepository.findById(id);
        userRepository.deleteById(id);
        return isRemoved;
    }

    public Optional<User> updateUser(Long id, User newUser){
       /* Optional<User> u  = userRepository.findById(id);
        User tempUser = new User();
        tempUser = u;
        user = tempUser ;
        userRepository.save(user);*/
        return userRepository.findById(id)
                .map(user -> {user.setUsername(newUser.getUsername());
                    user.setSozlesmeList(newUser.getSozlesmeList());
                    user.setPassword(newUser.getPassword());
                    user.setRoles(newUser.getRoles());
                    user.setEmail(newUser.getEmail());
                    user.setOkulKimlikNo(newUser.getOkulKimlikNo());
                    user.setTelno(newUser.getTelno());
                    user.setDogumTarihi(newUser.getDogumTarihi());
                    user.setKayitTarihi(newUser.getKayitTarihi());
                    user.setDonemSayisi(newUser.getDonemSayisi());
                    return userRepository.save(user);
                });
    }
    public User addPersonelIK(User user)
    {

        if(user.getRoles().toString().equals("ROLE_PERSONEL")){

            log.info("Adding user {}...",user);
            userRepository.save(user);

        }
        return user;
    }
    public User add(User user)
    {

        if(user.getRoles().toString().equals("ROLE_PERSONEL")){

            log.info("Adding user {}...",user);
            userRepository.save(user);

        }
        return user;
    }

    public Collection<UserRole> getUsersRolesByUserId(Long id){
        User user = userRepository.findByUserID(id);

        return user.getRoles();
    }

    public List<Sozlesme> getUsersSozlesmelerByUserId(Long id){
        User user = userRepository.findByUserID(id);

        return user.getSozlesmeList();
    }

}
