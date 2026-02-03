package com.arle.example.controller;

import com.arle.example.dto.PacienteDTO;
import com.arle.example.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
@CrossOrigin(origins = "*")  // Para pruebas con Postman
public class PacienteController {

    @Autowired
    private PacienteService service;

    @GetMapping
    public List<PacienteDTO> getAll() {
        return service.getAllPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> getById(@PathVariable Long id) {
        return service.getPacienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public PacienteDTO create(@RequestBody PacienteDTO dto) {
        return service.createPaciente(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PacienteDTO> update(@PathVariable Long id, @RequestBody PacienteDTO dto) {
        return ResponseEntity.ok(service.updatePaciente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deletePaciente(id);
        return ResponseEntity.noContent().build();
    }
}
