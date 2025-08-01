package com.descodeuses.planit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.descodeuses.planit.entity.UtilisateurEntity;
import com.descodeuses.planit.repository.UtilisateurRepository;

@Service
public class UtilisateurDetailsServiceImpl implements UserDetailsService {



    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurEntity user  = utilisateurRepository.findByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new User(user.getUsername(),
            user.getPassword(), List.of(new SimpleGrantedAuthority( user.getRole())));
    }

}
