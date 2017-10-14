package springcomtestes.demo.servico;

import springcomtestes.demo.modelo.Pessoa;
import springcomtestes.demo.modelo.Telefone;
import springcomtestes.demo.servico.exception.TelefoneNaoEncontradoExcetion;
import springcomtestes.demo.servico.exception.UnicidadeCpfException;
import springcomtestes.demo.servico.exception.UnidadeTelefoneExcepetion;

/**
 * Created by Crisley Alves on 12, out, 2017
 **/
public interface PessoaService {



    Pessoa salvar(Pessoa pessoa) throws UnicidadeCpfException, UnidadeTelefoneExcepetion;

    Pessoa buscarPorTelefone(Telefone telefone) throws TelefoneNaoEncontradoExcetion;
}
