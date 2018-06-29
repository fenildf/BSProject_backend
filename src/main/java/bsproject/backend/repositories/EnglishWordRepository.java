package bsproject.backend.repositories;

import org.springframework.data.repository.CrudRepository;
import bsproject.backend.entities.EnglishWord;

public interface EnglishWordRepository extends CrudRepository<EnglishWord, Integer> {

}