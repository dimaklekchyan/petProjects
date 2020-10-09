package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.inProgressBook.InProgressBook;
import org.springframework.data.repository.CrudRepository;

public interface InProgressRepository extends CrudRepository<InProgressBook, Integer> {
}
