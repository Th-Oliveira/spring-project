package com.th_oliveira.spring_boot_project.controller;

import com.th_oliveira.spring_boot_project.entity.UsuarioEntity;
import com.th_oliveira.spring_boot_project.security.JwtUtil;
import com.th_oliveira.spring_boot_project.service.UsuarioService;
import org.springframework.http.ResponseEntity;
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

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request){
        UsuarioEntity usuarioEntity = usuarioService.registrarUsuario(request.get("username"), request.get("password"));
        return ResponseEntity.ok(usuarioEntity);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request){
        Optional<UsuarioEntity> usuarioEntity = usuarioService.buscarPorUsername(request.get("username"));
        if(usuarioEntity.isPresent() && usuarioEntity.get().getPassword().equals(request.get("password"))){
            String token = JwtUtil.generateToken(usuarioEntity.get().getUsername());
            return ResponseEntity.ok(Map.of("token", token));
        }
        return ResponseEntity.status(401).body("Credenciais inválidas!");
    }
}
