package com.universdad.wheels.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.universdad.wheels.model.Viaje;
import com.universdad.wheels.repository.UsuarioRepository;
import com.universdad.wheels.repository.ViajeRepository;

@RestController
@RequestMapping("/api/viajes")
@CrossOrigin(origins = "*")
public class ViajeController {

    public final UsuarioRepository usuarioRepository;
    public final ViajeRepository viajeRepository;

    //Reocrdar: Esto es la inyección de las instancias del repositorio, para permitir el Crud
    public ViajeController(ViajeRepository viajeRepository, UsuarioRepository usuarioRepository){
        this.viajeRepository = viajeRepository;
        this.usuarioRepository = usuarioRepository;
    }

    //Get: Mostrar los viajes 
    @GetMapping
    public List<Viaje>getAllViajes(){
        return viajeRepository.findAll();
    }

    //Get para obtener un viaje por Id
    @GetMapping("/{id}")
    public ResponseEntity<Viaje> getById(@PathVariable Long id){
        Optional<Viaje> viaje = viajeRepository.findById(id);
        return viaje.map(ResponseEntity::ok).orElse (ResponseEntity.notFound().build());
    }

    //Post para crear un ivaje
    @PostMapping("/{idConductor}")
    public ResponseEntity<Viaje> createViaje(@PathVariable Long idConductor, @RequestBody Viaje viaje){
        return usuarioRepository.findById(idConductor)
            .map(conductor -> {
                viaje.setConductor(conductor);
                return ResponseEntity.ok(viajeRepository.save(viaje));
            })
            .orElse(ResponseEntity.badRequest().build());
    }

    //Put para actualizar viaje
    @PutMapping("/{id}")
    public ResponseEntity<Viaje> updateViaje(@PathVariable Long id, @RequestBody Viaje updatedViaje){
        return viajeRepository.findById(id)
            .map(viaje -> {
                viaje.setPuntoInicio(updatedViaje.getPuntoInicio());
                viaje.setPuntoFinal(updatedViaje.getPuntoFinal());
                viaje.setRuta(updatedViaje.getRuta());
                viaje.setHoraSalida(updatedViaje.getHoraSalida());
                viaje.setCuposDisponibles(updatedViaje.getCuposDisponibles());
                viaje.setTarifa(updatedViaje.getTarifa());
                viaje.setEstado(updatedViaje.getEstado());
                return ResponseEntity.ok(viajeRepository.save(viaje));
            })
            .orElse(ResponseEntity.notFound().build());
    }

    //Delete para eliminar viajes 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViaje(@PathVariable Long id){
        if(!viajeRepository.existsById(id))
        return ResponseEntity.notFound().build();
        viajeRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
