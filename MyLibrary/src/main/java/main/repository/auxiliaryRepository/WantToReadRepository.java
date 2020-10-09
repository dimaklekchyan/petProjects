package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.wantToReadBook.WantToReadBook;
import org.springframework.data.repository.CrudRepository;

public interface WantToReadRepository extends CrudRepository<WantToReadBook, Integer> {
}
