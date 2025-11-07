package com.universdad.wheels.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import  jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "viajes")
@Data   
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Viaje {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String puntoInicio;

    @NotBlank
    private String puntoFinal;

    private String ruta;
    private LocalDateTime horaSalida;
    private int cuposDisponibles;
    private double tarifa;

    @Enumerated(EnumType.STRING)
    private EstadoViaje estado; //Disponible, LLeno, Cancelado

    //Relación con el conductor (Usuario)
    @OneToOne
    @JoinColumn(name = "id_conductor")
    private Usuario conductor;

    //Relación con las reservas
    @OneToMany(mappedBy="viaje", cascade=CascadeType.ALL)
    private List<Reserva> reservas;

    public enum EstadoViaje {
        DISPONIBLE,
        LLENO,
        CANCELADO
    }
}
