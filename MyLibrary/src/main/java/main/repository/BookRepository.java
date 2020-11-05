package main.repository;

import main.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleOrAuthor(String title, String author);
    Book findById(long id);

}
