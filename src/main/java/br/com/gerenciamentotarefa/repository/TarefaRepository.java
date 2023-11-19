package br.com.gerenciamentotarefa.repository;

import br.com.gerenciamentotarefa.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    @Query(value = "select * from tarefa t order by t.dataconcluido asc", nativeQuery = true)
    List<Tarefa> findAll();
}
