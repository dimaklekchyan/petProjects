package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.BookWhichUserWantToRead.BookWhichUserWantToRead;
import org.springframework.data.repository.CrudRepository;

public interface BookWhichUserWantToReadRepository extends CrudRepository<BookWhichUserWantToRead, Integer> {
}
