package springcomtestes.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import springcomtestes.demo.modelo.Pessoa;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Crisley Alves on 14, out, 2017
 **/
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Test
    public void deveProcurarPessoaPeloCpf() {
        Optional<Pessoa> optional = pessoaRepository.findByCpf("18273849228");

        assertThat(optional.isPresent()).isTrue();

        Pessoa pessoa = optional.get();
        assertThat(pessoa.getCodigo()).isEqualTo(1L);
        assertThat(pessoa.getNome()).isEqualTo("Iago");
        assertThat(pessoa.getCpf()).isEqualTo("18273849228");
    }

    @Test
    public void naoDeveProcurarPessoaDeCpfExistente() {
        Optional<Pessoa> optional = pessoaRepository.findByCpf("92819203948");
        assertThat(optional.isPresent()).isFalse();
    }

    @Test
    public void deveListarTodasAsPessoas() {
        List<Pessoa> pessoas = pessoaRepository.findAll();
        assertThat(pessoas.size() > 0).isTrue();
    }

    @Test
    public void deveEncontrarPessoa() {
        Pessoa pessoa = pessoaRepository.findByCodigo(Long.parseLong("1"));
        assertThat(pessoa.getNome()).isNotEmpty();
    }

    @Test
    public void naoDeveEncontrarPessoa() {
        Pessoa pessoa = pessoaRepository.findByCodigo(Long.parseLong("99999"));
        assertThat(pessoa).isNull();
    }

    @Test
    public void verificaPermissaoUsuarioAutorizado(){
        Pessoa pessoa = pessoaRepository.findByNome("Iago");
        assertThat(pessoa).isNotNull();
        assertThat(pessoa.getPermissao()).isEqualTo(Integer.parseInt("1"));
        assertThat(pessoa.getNome()).isEqualTo("Iago");
    }

    @Test
    public void verificaPermissaoUsuarioNaoAutorizado(){
        Pessoa pessoa = pessoaRepository.findByNome("Crisley");
        assertThat(pessoa).isNotNull();
        assertThat(pessoa.getPermissao()).isNotEqualTo(Integer.parseInt("1"));
    }

    @Test
    public void verificaUsuarioNaoDeveTerTelefone(){
        Pessoa pessoa = pessoaRepository.findByNome("Crisley");
        assertThat(pessoa).isNotNull();
        assertThat(pessoa.getTelefones().size()).isEqualTo(0);
    }

    @Test
    public void verificaUsuarioTemPeloMenosUmTelefone(){
        Pessoa pessoa = pessoaRepository.findByNome("Iago");
        assertThat(pessoa).isNotNull();
        assertThat(pessoa.getTelefones().size()).isGreaterThanOrEqualTo(1);
    }



}
