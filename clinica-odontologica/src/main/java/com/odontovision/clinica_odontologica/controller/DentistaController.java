package com.odontovision.clinica_odontologica.controller;

import com.odontovision.clinica_odontologica.dto.DentistaDTO;
import com.odontovision.clinica_odontologica.exception.DentistaNotFoundException;
import com.odontovision.clinica_odontologica.service.DentistaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST responsável pelas operações com a entidade Dentista.
 * <p>
 * Este controlador fornece endpoints para criar, listar, buscar,
 * atualizar e desativar (soft delete) dentistas na aplicação.
 * </p>
 */
@RestController
@RequestMapping("/dentistas")
public class DentistaController {

    private final DentistaService dentistaService;

    public DentistaController(DentistaService dentistaService) {
        this.dentistaService = dentistaService;
    }

    /**
     * Criar um novo dentista.
     *
     * @param dentistaDTO Dados do dentista a ser criado.
     * @return Dentista criado.
     */
    @PostMapping
    public ResponseEntity<DentistaDTO> criarDentista(@Valid @RequestBody DentistaDTO dentistaDTO) {
        DentistaDTO novoDentista = dentistaService.salvarDentista(dentistaDTO);
        return new ResponseEntity<>(novoDentista, HttpStatus.CREATED);
    }

    /**
     * Listar todos os dentistas.
     *
     * @return Lista de dentistas.
     */
    @GetMapping
    public ResponseEntity<List<DentistaDTO>> listarDentistas() {
        List<DentistaDTO> dentistas = dentistaService.listarTodos();
        return ResponseEntity.ok(dentistas);
    }

    /**
     * Buscar dentista por ID.
     *
     * @param id ID do dentista.
     * @return Dentista encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DentistaDTO> buscarDentistaPorId(@PathVariable Long id) {
        return dentistaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new DentistaNotFoundException(id));
    }

    /**
     * Atualizar um dentista existente.
     *
     * @param id           ID do dentista.
     * @param dentistaDTO Dados atualizados do dentista.
     * @return Dentista atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DentistaDTO> atualizarDentista(@PathVariable Long id, @Valid @RequestBody DentistaDTO dentistaDTO) {
        return dentistaService.atualizarDentista(id, dentistaDTO)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new DentistaNotFoundException(id));
    }

    /**
     * Desativar um dentista (soft delete).
     * <p>
     * O dentista não é removido fisicamente do sistema, mas marcado como inativo.
     * </p>
     *
     * @param id ID do dentista.
     * @return Resposta vazia com status adequado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDentista(@PathVariable Long id) {
        dentistaService.desativarDentista(id);  // Soft delete
        return ResponseEntity.noContent().build();
    }
}
