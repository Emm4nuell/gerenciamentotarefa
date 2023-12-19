package br.com.gerenciamentotarefa.repository;

import br.com.gerenciamentotarefa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByEmail(String email);

    @Query(value = "select * from usuario as u where chaveativacao = :chave", nativeQuery = true)
    Optional<Usuario> findByChaveativacao(String chave);
}
