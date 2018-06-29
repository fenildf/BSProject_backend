package bsproject.backend.repositories;
import bsproject.backend.entities.Session;
import org.springframework.data.repository.CrudRepository;

public interface SQLSessionRepository extends CrudRepository<Session, Long> {
    boolean existsByEmail(String email);
    Session findByEmail(String email);
    boolean existsByToken(String token);
    Session findByToken(String token);
}