package main.repository.usersBook;

import main.model.usersBook.UsersBook;
import main.model.usersBook.UsersBookKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersBookRepository extends JpaRepository<UsersBook, UsersBookKey> {
    List<UsersBook> findAll();
}
