package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.usersBook.UsersBook;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersBookRepository extends CrudRepository<UsersBook, Integer> {
    List<UsersBook> findByUserId(long id);
}
