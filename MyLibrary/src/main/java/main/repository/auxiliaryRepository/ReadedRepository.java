package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.readedBook.ReadedBook;
import org.springframework.data.repository.CrudRepository;

public interface ReadedRepository extends CrudRepository<ReadedBook, Integer> {
}
