package springcomtestes.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import springcomtestes.demo.modelo.Pessoa;
import springcomtestes.demo.modelo.Telefone;

import java.util.List;

/**
 * Created by Crisley Alves on 14, out, 2017
 **/
public interface TelefoneRepository extends JpaRepository<Telefone, Long> {

    List<Telefone> findAllByPessoa (Pessoa pessoa);

    List<Telefone> findAllByNumero (String numero);

}
