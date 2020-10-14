package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.BookWhichUserAreReading.BookWhichUserAreReading;
import org.springframework.data.repository.CrudRepository;

public interface InProgressRepository extends CrudRepository<BookWhichUserAreReading, Integer> {
}
