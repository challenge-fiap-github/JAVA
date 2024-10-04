package com.odontovision.clinica_odontologica.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Entidade que representa um Diagnóstico na clínica odontológica.
 * <p>
 * Esta classe mapeia a tabela "diagnostico" no banco de dados e contém
 * as informações dos diagnósticos realizados nos pacientes.
 * </p>
 */
@Entity
@Getter
@Setter
public class Diagnostico {

    /**
     * Identificador único do diagnóstico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo de diagnóstico realizado.
     * <p>
     * Exemplo: "Cárie", "Gengivite", etc. Este campo é obrigatório.
     * </p>
     */
    @NotBlank(message = "O tipo de diagnóstico é obrigatório.")
    private String tipoDiagnostico;

    /**
     * Data em que o diagnóstico foi realizado.
     * <p>
     * Este campo é obrigatório e não pode ser nulo.
     * </p>
     */
    @NotNull(message = "A data do diagnóstico é obrigatória.")
    private LocalDate dataDiagnostico;

    /**
     * Descrição detalhada do diagnóstico.
     * <p>
     * Este campo deve conter observações adicionais sobre o diagnóstico.
     * </p>
     */
    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    /**
     * Relacionamento muitos-para-um com a entidade Paciente.
     * <p>
     * Cada diagnóstico está associado a um único paciente.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)  // Chave estrangeira para Paciente
    private Paciente paciente;
}