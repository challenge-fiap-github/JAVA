package com.odontovision.clinica_odontologica.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

/**
 * Entidade que representa um Dentista na clínica odontológica.
 * <p>
 * Esta classe mapeia a tabela "dentista" no banco de dados e contém
 * as informações dos dentistas que atuam na clínica. Cada dentista
 * pode estar associado a múltiplos procedimentos realizados.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dentista {

    /**
     * Identificador único do dentista.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nome completo do dentista.
     */
    private String nome;

    /**
     * Número de licença profissional do dentista.
     * <p>
     * Representa o registro profissional do dentista junto ao conselho
     * de classe (CRO - Conselho Regional de Odontologia).
     * </p>
     */
    private String numeroLicenca;

    /**
     * Lista de procedimentos associados ao dentista.
     * <p>
     * Indica todos os procedimentos realizados pelo dentista na clínica.
     * O relacionamento é unidirecional e mapeado pela propriedade "dentista"
     * na entidade {@link Procedimento}.
     * </p>
     */
    @OneToMany(mappedBy = "dentista", cascade = CascadeType.ALL)
    private List<Procedimento> procedimentos;
}
