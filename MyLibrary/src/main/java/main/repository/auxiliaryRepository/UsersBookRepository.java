package main.repository.auxiliaryRepository;

import main.model.Book;
import main.model.User;
import main.model.auxiliaryEntities.usersBook.UsersBook;
import main.model.auxiliaryEntities.usersBook.UsersBookKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersBookRepository extends JpaRepository<UsersBook, UsersBookKey> {
    //List<UsersBook> findByKeyBookAndKeyUser(Book book, User user);
}
