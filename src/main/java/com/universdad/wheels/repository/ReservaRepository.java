package com.universdad.wheels.repository;

import com.universdad.wheels.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


    public interface ReservaRepository extends JpaRepository<Reserva, Long> {
        List<Reserva> findByPasajeroId(Long pasajeroId);
        List<Reserva> findByViajeId(Long viajeId);

    }
