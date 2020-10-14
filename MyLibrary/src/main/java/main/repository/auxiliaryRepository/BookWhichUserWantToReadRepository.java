package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.BookWhichUserWantToRead.BookWhichUserWantToRead;
import org.springframework.data.repository.CrudRepository;

public interface WantToReadRepository extends CrudRepository<BookWhichUserWantToRead, Integer> {
}
