package com.th_oliveira.spring_boot_project.controller;

import com.th_oliveira.spring_boot_project.entity.usuario.UsuarioEntity;
import com.th_oliveira.spring_boot_project.entity.usuario.dto.request.LoginUserDTO;
import com.th_oliveira.spring_boot_project.entity.usuario.dto.request.RegisterUserDTO;
import com.th_oliveira.spring_boot_project.security.JwtUtil;
import com.th_oliveira.spring_boot_project.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public AuthController(UsuarioService usuarioService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usuarioService = usuarioService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserDTO dto){
        UsuarioEntity usuarioEntity = usuarioService.registrarUsuario(dto.username(), dto.password());
        return ResponseEntity.ok(usuarioEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginUserDTO dto) {
        Optional<UsuarioEntity> usuarioEntity = usuarioService.buscarPorUsername(dto.username());
        if(usuarioEntity.isPresent() && bCryptPasswordEncoder.matches(dto.password(), usuarioEntity.get().getPassword())) {
            String token = JwtUtil.generateToken(usuarioEntity.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas!");
    }
}
