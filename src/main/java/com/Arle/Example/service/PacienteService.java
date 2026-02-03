
package com.arle.example.service;

import com.arle.example.dto.PacienteDTO;
import com.arle.example.model.Paciente;
import com.arle.example.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacienteService {

    @Autowired
    private PacienteRepository repository;

    public List<PacienteDTO> getAllPacientes() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PacienteDTO> getPacienteById(Long id) {
        return repository.findById(id).map(this::toDTO);
    }

    public PacienteDTO createPaciente(PacienteDTO dto) {
        Paciente paciente = toEntity(dto);
        paciente = repository.save(paciente);
        return toDTO(paciente);
    }

    public PacienteDTO updatePaciente(Long id, PacienteDTO dto) {
        Paciente paciente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        paciente.setNombre(dto.getNombre());
        paciente.setEdad(dto.getEdad());
        paciente.setTelefono(dto.getTelefono());
        paciente.setCorreo(dto.getCorreo());
        paciente = repository.save(paciente);
        return toDTO(paciente);
    }

    public void deletePaciente(Long id) {
        repository.deleteById(id);
    }

    private PacienteDTO toDTO(Paciente p) {
        return new PacienteDTO(p.getIdPaciente(), p.getNombre(), p.getEdad(), p.getTelefono(), p.getCorreo());
    }

    private Paciente toEntity(PacienteDTO dto) {
        return new Paciente(null, dto.getNombre(), dto.getEdad(), dto.getTelefono(), dto.getCorreo());
    }
}
