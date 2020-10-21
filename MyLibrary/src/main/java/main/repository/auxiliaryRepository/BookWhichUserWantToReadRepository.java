package main.repository.auxiliaryRepository;

import main.model.Book;
import main.model.auxiliaryEntities.BookWhichUserWantToRead.BookWhichUserWantToRead;
import main.model.auxiliaryEntities.BookWhichUserWantToRead.BookWhichUserWantToReadKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookWhichUserWantToReadRepository extends JpaRepository<BookWhichUserWantToRead, BookWhichUserWantToReadKey> {

    @Query("SELECT book FROM Book as book " +
            "JOIN BookWhichUserWantToRead as reading ON reading.bookId = book.id " +
            "WHERE (book.title = :title OR book.author = :author) AND reading.userId = :userId")
    List<Book> findByTitleOrAuthor(String title, String author, int userId);
}
