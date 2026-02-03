package com.arle.example.controller;

import com.arle.example.dto.CitaDTO;
import com.arle.example.service.CitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/citas")
@CrossOrigin(origins = "*")
public class CitaController {

    @Autowired
    private CitaService service;

    @GetMapping
    public List<CitaDTO> getAll() {
        return service.getAllCitas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitaDTO> getById(@PathVariable Long id) {
        return service.getCitaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public CitaDTO create(@RequestBody CitaDTO dto) {
        return service.createCita(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitaDTO> update(@PathVariable Long id, @RequestBody CitaDTO dto) {
        return ResponseEntity.ok(service.updateCita(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteCita(id);
        return ResponseEntity.noContent().build();
    }
}
