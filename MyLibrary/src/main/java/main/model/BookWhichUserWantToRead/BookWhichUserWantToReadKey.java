package main.model.BookWhichUserWantToRead;

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
public class BookWhichUserWantToReadKey implements Serializable {

    @OneToOne
    private Book book;
    @OneToOne
    private User user;

    public BookWhichUserWantToReadKey() {
    }

    public BookWhichUserWantToReadKey(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}
