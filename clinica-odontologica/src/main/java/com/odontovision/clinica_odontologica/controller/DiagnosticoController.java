package com.odontovision.clinica_odontologica.controller;

import com.odontovision.clinica_odontologica.dto.DiagnosticoDTO;
import com.odontovision.clinica_odontologica.exception.DiagnosticoNotFoundException;
import com.odontovision.clinica_odontologica.service.DiagnosticoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    private final DiagnosticoService diagnosticoService;

    public DiagnosticoController(DiagnosticoService diagnosticoService) {
        this.diagnosticoService = diagnosticoService;
    }

    /**
     * Criar um novo diagnóstico.
     *
     * @param diagnosticoDTO Dados do diagnóstico a ser criado.
     * @return DiagnosticoDTO criado.
     */
    @PostMapping
    public ResponseEntity<DiagnosticoDTO> criarDiagnostico(@Valid @RequestBody DiagnosticoDTO diagnosticoDTO) {
        DiagnosticoDTO novoDiagnostico = diagnosticoService.salvarDiagnostico(diagnosticoDTO);
        return ResponseEntity.status(201).body(novoDiagnostico);
    }

    /**
     * Listar todos os diagnósticos.
     *
     * @return Lista de DiagnosticoDTO.
     */
    @GetMapping
    public ResponseEntity<List<DiagnosticoDTO>> listarDiagnosticos() {
        List<DiagnosticoDTO> diagnosticos = diagnosticoService.listarTodos();
        return ResponseEntity.ok(diagnosticos);
    }

    /**
     * Buscar um diagnóstico por ID.
     *
     * @param id ID do diagnóstico.
     * @return DiagnosticoDTO encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DiagnosticoDTO> buscarDiagnosticoPorId(@PathVariable Long id) {
        DiagnosticoDTO diagnostico = diagnosticoService.buscarPorId(id)
                .orElseThrow(() -> new DiagnosticoNotFoundException(id));
        return ResponseEntity.ok(diagnostico);
    }

    /**
     * Atualizar um diagnóstico existente.
     *
     * @param id              ID do diagnóstico a ser atualizado.
     * @param diagnosticoDTO Dados atualizados do diagnóstico.
     * @return DiagnosticoDTO atualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticoDTO> atualizarDiagnostico(@PathVariable Long id, @Valid @RequestBody DiagnosticoDTO diagnosticoDTO) {
        DiagnosticoDTO atualizado = diagnosticoService.atualizarDiagnostico(id, diagnosticoDTO)
                .orElseThrow(() -> new DiagnosticoNotFoundException(id));
        return ResponseEntity.ok(atualizado);
    }

    /**
     * Deletar um diagnóstico por ID.
     *
     * @param id ID do diagnóstico a ser deletado.
     * @return Resposta vazia com status adequado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarDiagnostico(@PathVariable Long id) {
        diagnosticoService.deletarDiagnostico(id);
        return ResponseEntity.noContent().build();
    }
}
