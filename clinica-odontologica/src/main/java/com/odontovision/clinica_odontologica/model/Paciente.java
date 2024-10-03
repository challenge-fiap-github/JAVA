package com.odontovision.clinica_odontologica.model;

import lombok.*;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
     * <p>
     * Este campo é obrigatório e deve ter entre 3 e 100 caracteres.
     * </p>
     */
    @NotBlank(message = "O nome do paciente é obrigatório.")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres.")
    private String nome;

    /**
     * CPF do paciente.
     * <p>
     * Este campo é obrigatório, único e deve seguir o formato de CPF (###.###.###-##).
     * </p>
     */
    @NotBlank(message = "O CPF é obrigatório.")
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "O CPF deve estar no formato ###.###.###-##")
    @Column(unique = true, nullable = false)
    private String cpf;

    /**
     * Idade do paciente.
     * <p>
     * Este campo é obrigatório e deve ser um número positivo.
     * </p>
     */
    @NotNull(message = "A idade do paciente é obrigatória.")
    private Integer idade;

    /**
     * Status ativo do paciente.
     * <p>
     * Utilizado para soft delete. Pacientes inativos ainda têm seus dados no sistema,
     * mas são marcados como inativos.
     * </p>
     */
    private Boolean ativo = true;

    /**
     * Plano odontológico do paciente (opcional).
     */
    private String planoOdontologico;

    /**
     * Lista de diagnósticos associados ao paciente.
     * <p>
     * Representa o relacionamento um-para-muitos com a entidade {@link Diagnostico}.
     * </p>
     */
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Diagnostico> diagnosticos;

    /**
     * Lista de procedimentos associados ao paciente.
     * <p>
     * Representa o relacionamento um-para-muitos com a entidade {@link Procedimento}.
     * </p>
     */
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Procedimento> procedimentos;
}
