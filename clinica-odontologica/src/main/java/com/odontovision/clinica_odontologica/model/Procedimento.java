package com.odontovision.clinica_odontologica.model;

import lombok.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

/**
 * Entidade que representa um Procedimento na clínica odontológica.
 * <p>
 * Esta classe mapeia a tabela "procedimento" no banco de dados e contém
 * as informações dos procedimentos realizados pelos dentistas nos pacientes.
 * Cada procedimento está associado a um paciente e a um dentista.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Procedimento {

    /**
     * Identificador único do procedimento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo do procedimento realizado.
     * Exemplo: "Limpeza", "Extração", "Canal", etc.
     * Este campo é obrigatório.
     */
    @NotNull(message = "O tipo de procedimento é obrigatório.")
    private String tipoProcedimento;

    /**
     * Data em que o procedimento foi realizado.
     * Este campo é obrigatório.
     */
    @NotNull(message = "A data do procedimento é obrigatória.")
    private LocalDate dataProcedimento;

    /**
     * Custo do procedimento em moeda local.
     * Deve ser um valor positivo.
     */
    @NotNull(message = "O custo do procedimento é obrigatório.")
    @Positive(message = "O custo deve ser um valor positivo.")
    private Double custo;

    /**
     * Paciente ao qual o procedimento está associado.
     * Representa o relacionamento muitos-para-um com a entidade {@link Paciente}.
     * Este campo é obrigatório.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    /**
     * Dentista que realizou o procedimento.
     * Representa o relacionamento muitos-para-um com a entidade {@link Dentista}.
     * Este campo é obrigatório.
     */
    @ManyToOne
    @JoinColumn(name = "dentista_id", nullable = false)
    private Dentista dentista;

    /**
     * Relacionamento opcional com Sinistro.
     * <p>
     * Um procedimento pode estar relacionado a um sinistro (caso haja suspeita de fraude).
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "sinistro_id", nullable = true)
    private Sinistro sinistro;

    /**
     * Indica se o procedimento é suspeito de fraude.
     * <p>
     * Este campo pode ser usado para marcar procedimentos que estão sob análise
     * por suspeita de fraude.
     * </p>
     */
    private boolean riscoFraude;
}
