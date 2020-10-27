package main.repository;

import main.model.Book;
import main.model.BookWhichUserWantToRead.BookWhichUserWantToRead;
import main.model.BookWhichUserWantToRead.BookWhichUserWantToReadKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookWhichUserWantToReadRepository extends JpaRepository<BookWhichUserWantToRead, BookWhichUserWantToReadKey> {

    @Query("SELECT book FROM Book as book " +
            "JOIN BookWhichUserWantToRead as reading ON reading.bookId = book.id " +
            "WHERE reading.userId = :userId " +
                    "AND book.title = (CASE WHEN :title != '' THEN :title ELSE book.title END) " +
                    "AND book.author = (CASE WHEN :author != '' THEN :author ELSE book.author END)")
    List<Book> findByTitleOrAuthor(String title, String author, long userId);
}
