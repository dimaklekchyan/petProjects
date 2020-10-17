package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.BookWhichUserFinished.BookWhichUserFinished;
import org.springframework.data.repository.CrudRepository;

public interface BookWhichUserFinishedRepository extends CrudRepository<BookWhichUserFinished, Integer> {
}
