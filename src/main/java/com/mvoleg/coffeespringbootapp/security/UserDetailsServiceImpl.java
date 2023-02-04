package com.mvoleg.coffeespringbootapp.security;

import com.mvoleg.coffeespringbootapp.model.entity.RoleEntity;
import com.mvoleg.coffeespringbootapp.model.entity.UserEntity;
import com.mvoleg.coffeespringbootapp.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntityFromDB = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + username + " not found"));

        return new User(
                userEntityFromDB.getUsername(),
                userEntityFromDB.getPassword(),
                mapRolesToGrantedAuthorities(userEntityFromDB.getRoles())
        );
    }

    private Collection<GrantedAuthority> mapRolesToGrantedAuthorities(List<RoleEntity> roles) {
        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
}
