package com.odontovision.clinica_odontologica.model;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;

/**
 * Entidade que representa um Sinistro na clínica odontológica.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sinistro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Tipo de sinistro (ex: fraude, uso indevido, etc).
     */
    private String tipoSinistro;

    /**
     * Descrição do sinistro.
     */
    private String descricao;

    /**
     * Data em que o sinistro ocorreu.
     */
    private LocalDate dataSinistro;

    @ManyToOne
    @JoinColumn(name = "procedimento_id")
    private Procedimento procedimento;

    @ManyToOne
    @JoinColumn(name = "dentista_id")
    private Dentista dentista;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    private boolean riscoFraude;
}
