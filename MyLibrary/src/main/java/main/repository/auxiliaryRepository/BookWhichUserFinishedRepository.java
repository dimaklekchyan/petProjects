package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.BookWhichUserFinished.BookWhichUserFinished;
import org.springframework.data.repository.CrudRepository;

public interface ReadedRepository extends CrudRepository<BookWhichUserFinished, Integer> {
}
