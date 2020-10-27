package main.repository;

import main.model.Book;
import main.model.BookWhichUserFinished.BookWhichUserFinished;
import main.model.BookWhichUserFinished.BookWhichUserFinishedKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookWhichUserFinishedRepository extends JpaRepository<BookWhichUserFinished, BookWhichUserFinishedKey> {

    @Query("SELECT book FROM Book as book " +
            "JOIN BookWhichUserFinished as reading ON reading.bookId = book.id " +
            "WHERE reading.userId = :userId " +
                    "AND book.title = (CASE WHEN :title != '' THEN :title ELSE book.title END) " +
                    "AND book.author = (CASE WHEN :author != '' THEN :author ELSE book.author END)")
    List<Book> findByTitleOrAuthor(String title, String author, long userId);
}
