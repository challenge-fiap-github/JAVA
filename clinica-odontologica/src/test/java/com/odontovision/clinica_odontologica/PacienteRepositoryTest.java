package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Paciente;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de teste para o repositório {@link PacienteRepository}.
 * <p>
 * Esta classe contém testes automatizados para verificar o comportamento
 * correto do repositório de {@link Paciente}, especialmente o método {@link PacienteRepository#findAll()}
 * que retorna todos os registros de pacientes no banco de dados.
 * </p>
 * <p>
 * Os testes utilizam a anotação {@link DataJpaTest} para configurar o contexto
 * de persistência, e os métodos da classe são executados em um banco de dados
 * em memória configurado automaticamente pelo Spring.
 * </p>
 */
@DataJpaTest
public class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    /**
     * Teste que verifica se o método {@link PacienteRepository#findAll()} retorna
     * corretamente todos os registros de pacientes.
     * <p>
     * Este teste verifica que a lista de pacientes não é nula e contém pelo menos
     * um registro.
     * </p>
     */
    @Test
    public void shouldReturnAllPacientes() {
        List<Paciente> pacientes = pacienteRepository.findAll();
        assertThat(pacientes).isNotNull();
        assertThat(pacientes.size()).isGreaterThan(0);
    }
}
