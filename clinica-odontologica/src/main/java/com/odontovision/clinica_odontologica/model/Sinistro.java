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

    /**
     * Identificador único do sinistro.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Data em que o sinistro ocorreu.
     */
    private LocalDate dataSinistro; // Verifique se o nome está correto

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
