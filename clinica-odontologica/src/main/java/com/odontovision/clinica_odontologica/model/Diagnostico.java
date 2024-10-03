package com.odontovision.clinica_odontologica.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
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
@Data
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
     * Exemplos: "Cárie", "Gengivite", "Periodontite", etc.
     * Este campo é obrigatório.
     * </p>
     */
    @NotBlank(message = "O tipo de diagnóstico é obrigatório.")
    private String tipoDiagnostico;

    /**
     * Data em que o diagnóstico foi realizado.
     * <p>
     * A data não pode ser no futuro.
     * </p>
     */
    @PastOrPresent(message = "A data do diagnóstico deve ser no passado ou presente.")
    private LocalDate dataDiagnostico;

    /**
     * Descrição detalhada do diagnóstico.
     * <p>
     * Inclui observações adicionais, sintomas, recomendações e outras informações relevantes.
     * Este campo é opcional, mas pode conter detalhes importantes.
     * </p>
     */
    private String descricao;

    /**
     * Paciente ao qual o diagnóstico está associado.
     * <p>
     * Representa o relacionamento muitos-para-um com a entidade {@link Paciente}.
     * </p>
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;
}
