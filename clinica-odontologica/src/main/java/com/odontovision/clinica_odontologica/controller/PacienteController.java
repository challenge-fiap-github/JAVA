package com.odontovision.clinica_odontologica.controller;

import com.odontovision.clinica_odontologica.dto.PacienteDTO;
import com.odontovision.clinica_odontologica.exception.PacienteNotFoundException;
import com.odontovision.clinica_odontologica.service.PacienteService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST para gerenciar operações relacionadas a pacientes.
 */
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    /**
     * Construtor com injeção de dependência.
     *
     * @param pacienteService Serviço de pacientes.
     */
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    /**
     * Lista todos os pacientes com cache.
     *
     * @return Lista de {@link PacienteDTO}.
     */
    @GetMapping
    @Cacheable("pacientes")
    public ResponseEntity<List<PacienteDTO>> listarPacientes() {
        List<PacienteDTO> pacientes = pacienteService.listarTodos();
        return ResponseEntity.ok(pacientes);
    }

    /**
     * Cria um novo paciente e limpa o cache.
     *
     * @param pacienteDTO Dados do paciente a ser criado.
     * @return {@link PacienteDTO} criado.
     */
    @PostMapping
    @CacheEvict(value = "pacientes", allEntries = true)
    public ResponseEntity<PacienteDTO> criarPaciente(@Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO novoPaciente = pacienteService.salvarPaciente(pacienteDTO);
        return new ResponseEntity<>(novoPaciente, HttpStatus.CREATED);
    }

    /**
     * Atualiza um paciente existente e limpa o cache.
     *
     * @param id          ID do paciente a ser atualizado.
     * @param pacienteDTO Dados atualizados do paciente.
     * @return {@link PacienteDTO} atualizado.
     */
    @PutMapping("/{id}")
    @CacheEvict(value = "pacientes", allEntries = true)
    public ResponseEntity<PacienteDTO> atualizarPaciente(@PathVariable Long id, @Valid @RequestBody PacienteDTO pacienteDTO) {
        PacienteDTO atualizado = pacienteService.atualizarPaciente(id, pacienteDTO)
                .orElseThrow(() -> new PacienteNotFoundException(id));
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Deleta um paciente e limpa o cache.
     *
     * @param id ID do paciente a ser deletado.
     * @return Resposta vazia com status adequado.
     */
    @DeleteMapping("/{id}")
    @CacheEvict(value = "pacientes", allEntries = true)
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        pacienteService.deletarPaciente(id)
                .orElseThrow(() -> new PacienteNotFoundException(id));
        return ResponseEntity.noContent().build();
    }
}
