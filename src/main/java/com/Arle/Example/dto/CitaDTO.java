package com.arle.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaDTO {
    private Long idCita;
    private LocalDate fecha;
    private Integer numConsultorio;
    private Long idPaciente;  // ID del paciente (no el objeto completo)
}
