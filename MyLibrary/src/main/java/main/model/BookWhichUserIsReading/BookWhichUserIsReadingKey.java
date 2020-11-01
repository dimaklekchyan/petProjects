package main.model.BookWhichUserIsReading;

import lombok.Data;
import lombok.EqualsAndHashCode;
import main.model.Book;
import main.model.User;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@EqualsAndHashCode
@Embeddable
public class BookWhichUserIsReadingKey implements Serializable {

    @OneToOne
    private Book book;
    @OneToOne
    private User user;

    public BookWhichUserIsReadingKey() {
    }

    public BookWhichUserIsReadingKey(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}
