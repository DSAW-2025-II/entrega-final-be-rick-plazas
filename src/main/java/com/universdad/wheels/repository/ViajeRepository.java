package com.universdad.wheels.repository;

import com.universdad.wheels.model.Viaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

    public interface ViajeRepository extends JpaRepository<Viaje, Long> {

        List<Viaje> findByCuposDisponiblesGreaterThan(int cupos); //Filtrar por cupos disponibles mayor a tanto
        List<Viaje> findByPuntoInicioContainingIgnoreCase(String puntoInicio); //Busca viajes con el punto inicio que busque

    }

    /* Punto 7, filtros de búsqueda
     * viajeRepository.findByPuntoInicioContainingIgnoreCase("Mazurén");
     * Encuentra viajes que vayan a Mazurén, salgan de Mazurén, pasen por Mazu´ren
     */