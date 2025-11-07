package com.universdad.wheels.controller;

import com.universdad.wheels.model.Usuario;
import com.universdad.wheels.repository.UsuarioRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins ="*") //No tengo ni idea, pero permite llamadas desde rEact
public class UsuarioController {

    public final UsuarioRepository usuarioRepository;
    
    public UsuarioController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    //Get: listar todos
    @GetMapping
    public List<Usuario> getAllUsuarios(){
        return usuarioRepository.findAll();
    } 

    //gEt: buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id); //Buscar por id de usuario
        return usuario.map(ResponseEntity::ok).orElse (ResponseEntity.notFound().build());
    }

    //Post: Crear al nuevo usuario
    @PostMapping
    public Usuario createUsuario(@RequestBody Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    //Actualizar datos
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> updateUsuario(@PathVariable Long id, @RequestBody Usuario updatedUser){
        return usuarioRepository.findById(id)
            .map(usuario -> {
                usuario.setNombre(updatedUser.getNombre());
                usuario.setApellido(updatedUser.getApellido());
                usuario.setCorreo(updatedUser.getCorreo());
                usuario.setTelefono(updatedUser.getTelefono());
                return ResponseEntity.ok(usuarioRepository.save(usuario));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    //Eliminar Usuario
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario (@PathVariable Long id){
        if(!usuarioRepository.existsById(id))
        return ResponseEntity.notFound().build();
            usuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
