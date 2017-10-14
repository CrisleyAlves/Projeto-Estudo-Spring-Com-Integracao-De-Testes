package springcomtestes.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import springcomtestes.demo.modelo.Pessoa;

import java.util.Optional;

/**
 * Created by Crisley Alves on 12, out, 2017
 **/
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    Optional<Pessoa> findByCpf(String cpf);

    Optional<Pessoa> findByTelefones(String ddd, String numero);
}
