package com.dio.jwt.service;

import com.dio.jwt.model.UserData;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDetailsServicesImplements implements UserDetailsService {

    final private BCryptPasswordEncoder  bcryptPasswordEncoder;

    public UserDetailsServicesImplements(BCryptPasswordEncoder bcryptPasswordEncoder) {
        this.bcryptPasswordEncoder = bcryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserData usuario = findUser(username);
        if(usuario == null){
            throw new UsernameNotFoundException(username);
        }
        return new User(usuario.getUser(), usuario.getPassword(), Collections.emptyList());
    }
    /**Simulando Data Base*/
    private UserData findUser(String username) {
        UserData us = new UserData("admin",bcryptPasswordEncoder.encode("admin"));
        return us;
    }
    public List<UserData> findAll(){
        List<UserData> us = new ArrayList<>();
        us.add(findUser("admin"));
        return us;
    }
}
