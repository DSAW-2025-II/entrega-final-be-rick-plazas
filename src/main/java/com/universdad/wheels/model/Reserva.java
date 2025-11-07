package com.universdad.wheels.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reservas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Reserva {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private int cuposReservados;
    private String puntoRecogida;
    private LocalDateTime fechaReserva;

    //Relación con el viaje
    @ManyToOne //Varias reservas pueden estar relacionadas a un mismo viaje
    @JoinColumn(name = "id_viaje")
    private Viaje viaje;

    //Relación con el pasajero
    @ManyToOne
    @JoinColumn(name = "id_pasajero")
    private Usuario pasajero;
 
}
