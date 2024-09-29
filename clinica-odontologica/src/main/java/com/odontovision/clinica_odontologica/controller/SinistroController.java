package com.odontovision.clinica_odontologica.controller;

import com.odontovision.clinica_odontologica.dto.SinistroDTO;
import com.odontovision.clinica_odontologica.service.SinistroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

/**
 * Controlador REST para gerenciar operações relacionadas a sinistros.
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

    // Métodos adicionais podem ser implementados conforme necessário.
}
