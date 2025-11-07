package com.universdad.wheels.security;

import com.universdad.wheels.model.Usuario;
import com.universdad.wheels.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;



@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public String register(@RequestBody Usuario usuario) {
        usuario.setContraseña(passwordEncoder.encode(usuario.getContraseña()));
        usuarioRepository.save(usuario);
        return "Usuario registrado correctamente";
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Optional<Usuario> userDB = usuarioRepository.findByCorreo(usuario.getCorreo());
        if (userDB.isPresent() && passwordEncoder.matches(usuario.getContraseña(), userDB.get().getContraseña())) {
            return jwtUtil.generateToken(usuario.getCorreo());
        } else {
            return "Credenciales inválidas";
        }
    }
}

