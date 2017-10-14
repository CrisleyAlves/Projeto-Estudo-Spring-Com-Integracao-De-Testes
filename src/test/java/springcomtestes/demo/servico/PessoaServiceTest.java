package springcomtestes.demo.servico;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;
import springcomtestes.demo.modelo.Pessoa;
import springcomtestes.demo.modelo.Telefone;
import springcomtestes.demo.repository.PessoaRepository;
import springcomtestes.demo.servico.exception.TelefoneNaoEncontradoExcetion;
import springcomtestes.demo.servico.exception.UnicidadeCpfException;
import springcomtestes.demo.servico.exception.UnidadeTelefoneExcepetion;
import springcomtestes.demo.servico.impl.PessoaServiceImpl;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Crisley Alves on 12, out, 2017
 **/
@RunWith(SpringRunner.class)
public class PessoaServiceTest {

    private static final String NOME = "Crisley Alves";
    private static final String CPF = "Crisley Alves";
    private static final String DDD = "54";
    private static final String NUMERO = "81214239";

    @MockBean
    private PessoaRepository pessoaRepository;

    private PessoaService sut;

    private Pessoa pessoa;

    Telefone telefone;

    @Before
    public void setUp() throws Exception {
        sut = new PessoaServiceImpl(pessoaRepository);
        pessoa = new Pessoa();
        pessoa.setNome(NOME);
        pessoa.setCpf(CPF);

        telefone = new Telefone();
        telefone.setDdd(DDD);
        telefone.setNumero(NUMERO);

        pessoa.setTelefones(Arrays.asList(telefone));

        Mockito.when(pessoaRepository.findByCpf(CPF)).thenReturn(Optional.empty());
        Mockito.when(pessoaRepository.findByTelefones(DDD, NUMERO)).thenReturn(Optional.empty());
    }

    @Test
    public void deveSalvarPessoaNoRepository() throws Exception {
        sut.salvar(pessoa);

        Mockito.verify(pessoaRepository).save(pessoa);
    }

    @Test(
            expected = UnicidadeCpfException.class
    )
    public void naoDeveSalvarPessoaComMesmoCpfNoRepository() throws Exception {
        Mockito.when(pessoaRepository.findByCpf(CPF))
                .thenReturn(Optional.of(pessoa));
        sut.salvar(pessoa);
    }

    @Test(
            expected = UnidadeTelefoneExcepetion.class
    )
    public void naoDeveSalvarDuasPessoasComMesmoTelefone() throws Exception {
        Mockito.when(pessoaRepository.findByTelefones(DDD, NUMERO))
                .thenReturn(Optional.of(pessoa));
        sut.salvar(pessoa);
    }

    @Test(
            expected = TelefoneNaoEncontradoExcetion.class
    )
    public void deveRetornarExcecaoDeNaoEncontradoQuandoNaoExistirPessoaComDddENumeroDeTelefone() throws Exception{
        sut.buscarPorTelefone(telefone);
    }

    @Test
    public void deveProcurarPessoaPeloDddENumeroDoTelefone() throws Exception{
        /*
            Quando eu for no banco e executar tal método quero que me retorne o objeto pessoa
            contendo estes dados
         */
        Mockito.when(pessoaRepository.findByTelefones(DDD, NUMERO))
                .thenReturn(Optional.of(pessoa));

        Pessoa pessoaTeste = sut.buscarPorTelefone(telefone);

        Mockito.verify(pessoaRepository).findByTelefones(DDD, NUMERO);

        assertThat(pessoaTeste).isNotNull();
        assertThat(pessoaTeste.getNome()).isEqualTo(NOME);
        assertThat(pessoaTeste.getCpf()).isEqualTo(CPF);


    }


}
