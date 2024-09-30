package com.odontovision.clinica_odontologica.model;

import lombok.*;

import jakarta.persistence.*;
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
     */
    private String tipoProcedimento;

    /**
     * Data em que o procedimento foi realizado.
     */
    private LocalDate dataProcedimento;

    /**
     * Custo do procedimento em moeda local.
     */
    private Double custo;

    /**
     * Paciente ao qual o procedimento está associado.
     * Representa o relacionamento muitos-para-um com a entidade {@link Paciente}.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    /**
     * Dentista que realizou o procedimento.
     * Representa o relacionamento muitos-para-um com a entidade {@link Dentista}.
     */
    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private Dentista dentista;

    /**
     * Indica se o procedimento é suspeito de fraude.
     */
    private boolean riscoFraude;
}
