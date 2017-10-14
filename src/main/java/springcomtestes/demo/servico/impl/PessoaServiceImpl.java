package springcomtestes.demo.servico.impl;

import springcomtestes.demo.modelo.Pessoa;
import springcomtestes.demo.modelo.Telefone;
import springcomtestes.demo.repository.PessoaRepository;
import springcomtestes.demo.servico.exception.TelefoneNaoEncontradoExcetion;
import springcomtestes.demo.servico.exception.UnicidadeCpfException;
import springcomtestes.demo.servico.exception.UnidadeTelefoneExcepetion;

import java.util.Optional;

/**
 * Created by Crisley Alves on 12, out, 2017
 **/
public class PessoaServiceImpl implements springcomtestes.demo.servico.PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaServiceImpl(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    @Override
    public Pessoa salvar(Pessoa pessoa) throws UnicidadeCpfException, UnidadeTelefoneExcepetion {

        Optional<Pessoa> optional = pessoaRepository.findByCpf(pessoa.getCpf());

        if(optional.isPresent()){
            throw new UnicidadeCpfException();
        }

        final String ddd = pessoa.getTelefones().get(0).getDdd();
        final String numero = pessoa.getTelefones().get(0).getNumero();
        optional = pessoaRepository.findByTelefones(ddd, numero);

        if(optional.isPresent()){
            throw new UnidadeTelefoneExcepetion();
        }


        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa buscarPorTelefone(Telefone telefone) throws TelefoneNaoEncontradoExcetion {

        final Optional<Pessoa> optional = pessoaRepository.findByTelefones(telefone.getDdd(), telefone.getNumero());

        return optional.orElseThrow(() -> new TelefoneNaoEncontradoExcetion());
    }
}
