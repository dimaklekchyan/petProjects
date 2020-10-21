package main.repository.auxiliaryRepository;

import main.model.Book;
import main.model.auxiliaryEntities.BookWhichUserIsReading.BookWhichUserIsReading;
import main.model.auxiliaryEntities.BookWhichUserIsReading.BookWhichUserIsReadingKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookWhichUserIsReadingRepository extends JpaRepository<BookWhichUserIsReading, BookWhichUserIsReadingKey> {

    @Query("SELECT book FROM Book as book " +
            "JOIN BookWhichUserIsReading as reading ON reading.bookId = book.id " +
            "WHERE (book.title = :title OR book.author = :author) AND reading.userId = :userId")
    List<Book> findByTitleOrAuthor(String title, String author, int userId);
}
