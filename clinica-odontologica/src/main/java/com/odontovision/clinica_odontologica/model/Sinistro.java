package com.odontovision.clinica_odontologica.model;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa um Sinistro na clínica odontológica.
 * <p>
 * Esta classe mapeia a tabela "sinistro" no banco de dados e contém
 * as informações dos sinistros registrados, associando procedimentos,
 * dentistas e pacientes.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sinistro {

    /**
     * Identificador único do sinistro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo de sinistro.
     * Exemplo: "Suspeita de Fraude", "Uso Indevido", etc.
     */
    private String tipoSinistro;

    /**
     * Descrição detalhada do sinistro.
     * Pode incluir informações adicionais sobre o motivo ou contexto do sinistro.
     */
    private String descricao;

    /**
     * Data em que o sinistro foi registrado.
     */
    private LocalDate dataOcorrencia;

    /**
     * Procedimento relacionado ao sinistro.
     * Representa o relacionamento muitos-para-um com a entidade {@link Procedimento}.
     */
    @ManyToOne
    @JoinColumn(name = "procedimento_id", nullable = false)
    private Procedimento procedimento;

    /**
     * Dentista relacionado ao sinistro.
     * Representa o relacionamento muitos-para-um com a entidade {@link Dentista}.
     */
    @ManyToOne
    @JoinColumn(name = "dentista_id", nullable = false)
    private Dentista dentista;

    /**
     * Paciente relacionado ao sinistro.
     * Representa o relacionamento muitos-para-um com a entidade {@link Paciente}.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    /**
     * Indica se o sinistro é suspeito de fraude.
     */
    private boolean riscoFraude;
}
