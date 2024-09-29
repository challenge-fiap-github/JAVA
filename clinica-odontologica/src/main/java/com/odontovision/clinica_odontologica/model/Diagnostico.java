package com.odontovision.clinica_odontologica.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;


import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa um Diagnóstico na clínica odontológica.
 * <p>
 * Esta classe mapeia a tabela "diagnostico" no banco de dados e contém
 * as informações dos diagnósticos realizados em pacientes. Cada diagnóstico
 * está associado a um paciente específico.
 * </p>
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostico {

    /**
     * Identificador único do diagnóstico.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo do diagnóstico realizado.
     * <p>
     * Por exemplo: "Cárie", "Gengivite", "Periodontite", etc.
     * </p>
     */
    private String tipoDiagnostico;

    /**
     * Data em que o diagnóstico foi realizado.
     */
    private LocalDate dataDiagnostico;

    /**
     * Descrição detalhada do diagnóstico.
     * <p>
     * Inclui observações adicionais, sintomas, recomendações e outras informações relevantes.
     * </p>
     */
    private String descricao;

    /**
     * Paciente ao qual o diagnóstico está associado.
     * <p>
     * Representa o relacionamento muitos-para-um com a entidade {@link Paciente}.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
