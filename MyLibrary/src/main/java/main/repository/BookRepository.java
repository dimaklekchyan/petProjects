package main.repository;

import main.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    List<Book> findByTitleOrAuthor(String title, String author);
    Book findById(long id);
    List<Book> findByIdIn(Collection<Long> id);

}
