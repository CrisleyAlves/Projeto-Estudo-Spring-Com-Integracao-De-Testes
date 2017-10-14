package springcomtestes.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springcomtestes.demo.modelo.Pessoa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Crisley Alves on 12, out, 2017
 **/
public interface PessoaRepository extends JpaRepository<Pessoa, Long>{

    List<Pessoa> pessoas = new ArrayList<>();

    Optional<Pessoa> findByCpf(String cpf);

    Optional<Pessoa> findByTelefones(String ddd, String numero);

    Pessoa findByCodigo(Long codigo);

    Pessoa findByNome(String nome);
}
