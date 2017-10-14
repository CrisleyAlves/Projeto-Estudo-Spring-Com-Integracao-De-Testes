package springcomtestes.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import springcomtestes.demo.modelo.Endereco;
import springcomtestes.demo.modelo.Pessoa;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Crisley Alves on 14, out, 2017
 **/
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class EnderecoRepositoryTest {

    @Autowired
    EnderecoRepository enderecoRepository;

    Pessoa pessoaTeste = new Pessoa();

    @Test
    public void deveEncontrarEnderecoPessoa(){
        pessoaTeste.setCodigo(Long.parseLong("1"));
        Endereco endereco = enderecoRepository.findByPessoa(pessoaTeste);

        assertThat(endereco.getPessoa()).isEqualTo(pessoaTeste);
        assertThat(endereco.getPessoa().getCodigo()).isEqualTo(pessoaTeste.getCodigo());
    }

}
