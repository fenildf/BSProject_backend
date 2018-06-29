package bsproject.backend.repositories;

import bsproject.backend.entities.Recited;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface RecitedRepository extends JpaRepository<Recited, Integer> {

}