package com.Arle.Example.service;

import com.Arle.Example.dto.CitaDTO;
import com.Arle.Example.model.Cita;
import com.Arle.Example.model.Paciente;
import com.Arle.Example.repository.CitaRepository;
import com.Arle.Example.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CitaService {

    @Autowired
    private CitaRepository repository;

    @Autowired
    private PacienteRepository pacienteRepository;

    public List<CitaDTO> getAllCitas() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<CitaDTO> getCitaById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public CitaDTO createCita(CitaDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado con ID: " + dto.getIdPaciente()));
        Cita cita = new Cita();
        cita.setFecha(dto.getFecha());
        cita.setNumConsultorio(dto.getNumConsultorio());
        cita.setPaciente(paciente);
        cita = repository.save(cita);
        return toDTO(cita);
    }

    public CitaDTO updateCita(Long id, CitaDTO dto) {
        Cita cita = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cita no encontrada"));
        Paciente paciente = pacienteRepository.findById(dto.getIdPaciente())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        cita.setFecha(dto.getFecha());
        cita.setNumConsultorio(dto.getNumConsultorio());
        cita.setPaciente(paciente);
        cita = repository.save(cita);
        return toDTO(cita);
    }

    public void deleteCita(Long id) {
        repository.deleteById(id);
    }

    private CitaDTO toDTO(Cita c) {
        return new CitaDTO(c.getIdCita(), c.getFecha(), c.getNumConsultorio(), c.getPaciente().getIdPaciente());
    }
}