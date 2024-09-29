package com.odontovision.clinica_odontologica.repository;

import com.odontovision.clinica_odontologica.model.Sinistro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Classe de teste para o repositório {@link SinistroRepository}.
 * <p>
 * Esta classe contém testes automatizados para verificar o comportamento
 * correto do repositório de {@link Sinistro}, especialmente o método {@link SinistroRepository#findAll()}
 * que retorna todos os registros de sinistros no banco de dados.
 * </p>
 * <p>
 * Os testes utilizam a anotação {@link DataJpaTest} para configurar o contexto
 * de persistência, e os métodos da classe são executados em um banco de dados
 * em memória configurado automaticamente pelo Spring.
 * </p>
 */
@DataJpaTest
public class SinistroRepositoryTest {

    @Autowired
    private SinistroRepository sinistroRepository;

    /**
     * Teste que verifica se o método {@link SinistroRepository#findAll()} retorna
     * corretamente todos os registros de sinistros.
     * <p>
     * Este teste verifica que a lista de sinistros não é nula e contém pelo menos
     * um registro.
     * </p>
     */
    @Test
    public void shouldReturnAllSinistros() {
        List<Sinistro> sinistros = sinistroRepository.findAll();
        assertThat(sinistros).isNotNull();
        assertThat(sinistros.size()).isGreaterThan(0);
    }
}
