package com.odontovision.clinica_odontologica.controller;

import com.odontovision.clinica_odontologica.dto.SinistroDTO;
import com.odontovision.clinica_odontologica.exception.SinistroNotFoundException;
import com.odontovision.clinica_odontologica.service.SinistroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST para gerenciar operações relacionadas a sinistros.
 * <p>
 * Oferece endpoints para criar, listar, buscar, atualizar e deletar sinistros.
 * </p>
 */
@RestController
@RequestMapping("/sinistros")
public class SinistroController {

    private final SinistroService sinistroService;

    /**
     * Construtor com injeção de dependência.
     *
     * @param sinistroService Serviço de sinistros.
     */
    public SinistroController(SinistroService sinistroService) {
        this.sinistroService = sinistroService;
    }

    /**
     * Cria um novo sinistro.
     *
     * @param sinistroDTO Dados do sinistro a ser criado.
     * @return {@link SinistroDTO} criado.
     */
    @PostMapping
    public ResponseEntity<SinistroDTO> criarSinistro(@Valid @RequestBody SinistroDTO sinistroDTO) {
        SinistroDTO novoSinistro = sinistroService.salvarSinistro(sinistroDTO);
        return ResponseEntity.status(201).body(novoSinistro);
    }

    /**
     * Lista todos os sinistros.
     *
     * @return Lista de {@link SinistroDTO}.
     */
    @GetMapping
    public ResponseEntity<List<SinistroDTO>> listarSinistros() {
        List<SinistroDTO> sinistros = sinistroService.listarTodos();
        return ResponseEntity.ok(sinistros);
    }

    /**
     * Busca um sinistro por ID.
     *
     * @param id ID do sinistro.
     * @return {@link SinistroDTO} encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SinistroDTO> buscarSinistroPorId(@PathVariable Long id) {
        SinistroDTO sinistro = sinistroService.buscarPorId(id)
                .orElseThrow(() -> new SinistroNotFoundException(id));
        return ResponseEntity.ok(sinistro);
    }

    /**
     * Atualiza um sinistro existente.
     *
     * @param id          ID do sinistro a ser atualizado.
     * @param sinistroDTO Dados atualizados do sinistro.
     * @return {@link SinistroDTO} atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SinistroDTO> atualizarSinistro(@PathVariable Long id, @Valid @RequestBody SinistroDTO sinistroDTO) {
        SinistroDTO atualizado = sinistroService.atualizarSinistro(id, sinistroDTO)
                .orElseThrow(() -> new SinistroNotFoundException(id));
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Deleta um sinistro pelo ID.
     *
     * @param id ID do sinistro a ser deletado.
     * @return Resposta vazia com status adequado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarSinistro(@PathVariable Long id) {
        sinistroService.deletarSinistro(id);
        return ResponseEntity.noContent().build();
    }


}
