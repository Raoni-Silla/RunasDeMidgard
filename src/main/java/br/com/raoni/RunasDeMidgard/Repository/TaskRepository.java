package br.com.raoni.RunasDeMidgard.Repository;

import br.com.raoni.RunasDeMidgard.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    Optional<Task> findById(Long id);

}
