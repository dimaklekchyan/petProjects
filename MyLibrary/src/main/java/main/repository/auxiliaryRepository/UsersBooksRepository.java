package main.repository.auxiliaryRepository;

import main.model.auxiliaryEntities.usersBooks.UsersBooks;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersBooksRepository extends CrudRepository<UsersBooks, Integer> {
    List<UsersBooks> findByUserId(long id);
}
