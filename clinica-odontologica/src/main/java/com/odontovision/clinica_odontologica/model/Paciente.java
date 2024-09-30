package com.odontovision.clinica_odontologica.model;

import lombok.*;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidade que representa um Paciente na clínica odontológica.
 * <p>
 * Esta classe mapeia a tabela "paciente" no banco de dados e contém
 * as informações dos pacientes atendidos pela clínica.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paciente {

    /**
     * Identificador único do paciente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo do paciente.
     */
    private String nome;

    /**
     * Idade do paciente.
     */
    private Integer idade;

    /**
     * Plano odontológico do paciente.
     */
    private String planoOdontologico;

    /**
     * Lista de diagnósticos associados ao paciente.
     * <p>
     * Representa o relacionamento um-para-muitos com a entidade {@link Diagnostico}.
     * </p>
     */
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Diagnostico> diagnosticos;

    /**
     * Lista de procedimentos associados ao paciente.
     * <p>
     * Representa o relacionamento um-para-muitos com a entidade {@link Procedimento}.
     * </p>
     */
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Procedimento> procedimentos;
}
