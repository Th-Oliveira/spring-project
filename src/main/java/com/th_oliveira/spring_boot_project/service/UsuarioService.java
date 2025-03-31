package com.th_oliveira.spring_boot_project.service;

import com.th_oliveira.spring_boot_project.entity.UsuarioEntity;
import com.th_oliveira.spring_boot_project.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public UsuarioEntity registrarUsuario(String username, String password){
        String encodedPassword = passwordEncoder.encode(password);
        UsuarioEntity usuarioEntity = new UsuarioEntity(username, encodedPassword);
        return usuarioRepository.save(usuarioEntity);
    }

    public Optional<UsuarioEntity> buscarPorUsername(String username){
        return usuarioRepository.findByUsername(username);
    }


}
