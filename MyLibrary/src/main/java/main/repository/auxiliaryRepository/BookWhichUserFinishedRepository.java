package main.repository.auxiliaryRepository;

import main.model.Book;
import main.model.auxiliaryEntities.BookWhichUserFinished.BookWhichUserFinished;
import main.model.auxiliaryEntities.BookWhichUserFinished.BookWhichUserFinishedKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookWhichUserFinishedRepository extends JpaRepository<BookWhichUserFinished, BookWhichUserFinishedKey> {

    @Query("SELECT book FROM Book as book " +
            "JOIN BookWhichUserFinished as reading ON reading.bookId = book.id " +
            "WHERE (book.title = :title OR book.author = :author) AND reading.userId = :userId")
    List<Book> findByTitleOrAuthor(String title, String author, int userId);
}
