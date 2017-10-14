package springcomtestes.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springcomtestes.demo.modelo.Endereco;
import springcomtestes.demo.modelo.Pessoa;

/**
 * Created by Crisley Alves on 14, out, 2017
 **/
public interface EnderecoRepository extends JpaRepository<Endereco, Long>{

    Endereco findByPessoa(Pessoa pessoa);

}
