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
 * <p>
 * Este controlador oferece endpoints para criar, listar, atualizar e deletar pacientes.
 * Utiliza cache para melhorar a performance em consultas frequentes.
 * </p>
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
     * Lista todos os pacientes ativos com cache.
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
     * Busca um paciente por ID.
     *
     * @param id ID do paciente a ser buscado.
     * @return {@link PacienteDTO} encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PacienteDTO> buscarPacientePorId(@PathVariable Long id) {
        PacienteDTO paciente = pacienteService.buscarPorId(id)
                .orElseThrow(() -> new PacienteNotFoundException(id));  // Retorno sendo utilizado
        return ResponseEntity.ok(paciente);  // Retorno ResponseEntity
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
                .orElseThrow(() -> new PacienteNotFoundException(id));  // Retorno sendo utilizado
        return ResponseEntity.ok(atualizado);  // Retorno atualizado
    }

    /**
     * "Desativa" (soft delete) um paciente pelo ID e limpa o cache.
     * <p>
     * Em vez de remover o paciente do banco de dados, ele será marcado como inativo
     * utilizando soft delete.
     * O método verifica se o paciente existe antes de realizar a desativação. Se o paciente
     * não for encontrado, uma exceção {@link PacienteNotFoundException} é lançada.
     * </p>
     *
     * @param id ID do paciente a ser desativado.
     * @return Resposta vazia com status adequado (204 No Content).
     */
    @DeleteMapping("/{id}")
    @CacheEvict(value = "pacientes", allEntries = true)
    public ResponseEntity<Void> deletarPaciente(@PathVariable Long id) {
        // Verifica se o paciente existe; se não existir, lança exceção
        pacienteService.buscarPorId(id)
                .ifPresentOrElse(
                        paciente -> pacienteService.desativarPaciente(id),
                        () -> { throw new PacienteNotFoundException(id); }
                );

        // Retorna resposta 204 No Content
        return ResponseEntity.noContent().build();
    }
}
