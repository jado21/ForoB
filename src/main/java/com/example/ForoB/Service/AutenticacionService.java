package com.example.ForoB.Service;

import com.example.ForoB.Repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AutenticacionService implements UserDetailsService {
    private final UsuarioRepository repository;

    public AutenticacionService(UsuarioRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return repository.findByLogin(username);
    }
}