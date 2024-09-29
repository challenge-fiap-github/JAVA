package com.odontovision.clinica_odontologica.controller;

import com.odontovision.clinica_odontologica.dto.ProcedimentoDTO;
import com.odontovision.clinica_odontologica.exception.ProcedimentoNotFoundException;
import com.odontovision.clinica_odontologica.service.ProcedimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Controlador REST para gerenciar operações relacionadas a procedimentos.
 */
@RestController
@RequestMapping("/procedimentos")
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    /**
     * Construtor com injeção de dependência.
     *
     * @param procedimentoService Serviço de procedimentos.
     */
    public ProcedimentoController(ProcedimentoService procedimentoService) {
        this.procedimentoService = procedimentoService;
    }

    /**
     * Cria um novo procedimento.
     *
     * @param procedimentoDTO Dados do procedimento a ser criado.
     * @return {@link ProcedimentoDTO} criado.
     */
    @PostMapping
    public ResponseEntity<ProcedimentoDTO> criarProcedimento(@Valid @RequestBody ProcedimentoDTO procedimentoDTO) {
        ProcedimentoDTO novoProcedimento = procedimentoService.salvarProcedimento(procedimentoDTO);
        return ResponseEntity.status(201).body(novoProcedimento);
    }

    /**
     * Lista todos os procedimentos.
     *
     * @return Lista de {@link ProcedimentoDTO}.
     */
    @GetMapping
    public ResponseEntity<List<ProcedimentoDTO>> listarProcedimentos() {
        List<ProcedimentoDTO> procedimentos = procedimentoService.listarTodos();
        return ResponseEntity.ok(procedimentos);
    }

    /**
     * Busca um procedimento por ID.
     *
     * @param id ID do procedimento.
     * @return {@link ProcedimentoDTO} encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProcedimentoDTO> buscarProcedimentoPorId(@PathVariable Long id) {
        ProcedimentoDTO procedimento = procedimentoService.buscarPorId(id)
                .orElseThrow(() -> new ProcedimentoNotFoundException(id));
        return ResponseEntity.ok(procedimento);
    }

    /**
     * Atualiza um procedimento existente.
     *
     * @param id               ID do procedimento a ser atualizado.
     * @param procedimentoDTO Dados atualizados do procedimento.
     * @return {@link ProcedimentoDTO} atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProcedimentoDTO> atualizarProcedimento(@PathVariable Long id, @Valid @RequestBody ProcedimentoDTO procedimentoDTO) {
        ProcedimentoDTO procedimentoAtualizado = procedimentoService.atualizarProcedimento(id, procedimentoDTO)
                .orElseThrow(() -> new ProcedimentoNotFoundException(id));
        return ResponseEntity.ok(procedimentoAtualizado);
    }

    /**
     * Deleta um procedimento.
     *
     * @param id ID do procedimento a ser deletado.
     * @return Resposta vazia com status adequado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarProcedimento(@PathVariable Long id) {
        procedimentoService.deletarProcedimento(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Lista procedimentos com suspeita de fraude.
     *
     * @return Lista de {@link ProcedimentoDTO} com risco de fraude.
     */
    @GetMapping("/suspeitas-fraude")
    public ResponseEntity<List<ProcedimentoDTO>> listarProcedimentosComFraude() {
        List<ProcedimentoDTO> procedimentos = procedimentoService.listarFraudes();
        return ResponseEntity.ok(procedimentos);
    }
}
