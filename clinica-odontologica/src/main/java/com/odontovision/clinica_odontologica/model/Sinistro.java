package com.odontovision.clinica_odontologica.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa um Sinistro na clínica odontológica.
 * Esta classe mapeia a tabela "sinistro" no banco de dados e contém
 * as informações dos sinistros registrados.
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
     * Tipo do sinistro.
     * Exemplo: Suspeita de fraude, uso indevido.
     */
    private String tipoSinistro;

    /**
     * Descrição detalhada do sinistro.
     */
    private String descricao;

    /**
     * Data em que o sinistro ocorreu.
     */
    private LocalDate dataSinistro;

    /**
     * Procedimento relacionado ao sinistro.
     */
    @ManyToOne
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;

    /**
     * Dentista relacionado ao sinistro.
     */
    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private Dentista dentista;

    /**
     * Paciente relacionado ao sinistro.
     */
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    /**
     * Indica se o sinistro é suspeito de fraude.
     */
    private boolean riscoFraude;
}
