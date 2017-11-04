package springcomtestes.demo.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import springcomtestes.demo.modelo.Pessoa;
import springcomtestes.demo.modelo.Telefone;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Crisley Alves on 14, out, 2017
 **/
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")
public class TelefoneRepositoryTest {

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    Pessoa pessoaTeste = new Pessoa();

    @Test
    public void deveEncontrarTelefonePessoa(){
        pessoaTeste.setCodigo(Long.parseLong("1"));
        List<Telefone> telefones = telefoneRepository.findAllByPessoa(pessoaTeste);
        assertThat(telefones.get(0).getPessoa().getCodigo()).isEqualTo(pessoaTeste.getCodigo());
        assertThat(telefones.get(0).getNumero()).isEqualTo("11111111111");
    }

    @Test
    public void NaodeveEncontrarTelefonePessoa(){
        pessoaTeste.setCodigo(Long.parseLong("1"));
        List<Telefone> telefones = telefoneRepository.findAllByPessoa(pessoaTeste);
        assertThat(telefones.get(0).getPessoa().getCodigo()).isEqualTo(pessoaTeste.getCodigo());
        assertThat(telefones.get(0).getNumero()).isNotIn("99999999999");
    }

    @Test
    public void NenhumTelefoneParaContato(){
        pessoaTeste.setCodigo(Long.parseLong("6"));
        List<Telefone> telefones = telefoneRepository.findAllByPessoa(pessoaTeste);
        assertThat(telefones).isNotNull();
        pessoaTeste.setTelefones(telefones);
        assertThat(pessoaTeste.getTelefones().size()).isEqualTo(Integer.parseInt("0"));
    }

    @Test
    public void PeloMenosUmTelefoneParaContatoPositivo(){
        pessoaTeste.setCodigo(Long.parseLong("1"));
        List<Telefone> telefones = telefoneRepository.findAllByPessoa(pessoaTeste);
        assertThat(telefones).isNotNull();
        pessoaTeste.setTelefones(telefones);
        assertThat(pessoaTeste.getTelefones().size()).isGreaterThan(Integer.parseInt("0"));
    }

    @Test
    public void garanteTelefoneDaPessoaCorreta(){
        pessoaTeste = pessoaRepository.findByNome("Iago");
        List<Telefone> telefones = telefoneRepository.findAllByPessoa(pessoaTeste);
        assertThat(telefones.get(0).getPessoa()).isEqualTo(pessoaTeste);
    }

    @Test
    public void garanteTelefoneTemUmDono(){
        List<Telefone> telefones = telefoneRepository.findAllByNumero("11111111111");
        assertThat(telefones.get(0).getPessoa()).isNotNull();
        assertThat(telefones.get(0).getPessoa().getNome()).isEqualTo("Iago");
    }



}
