package main.model.BookWhichUserFinished;

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
public class BookWhichUserFinishedKey implements Serializable {

    @OneToOne
    private Book book;
    @OneToOne
    private User user;

    public BookWhichUserFinishedKey() {
    }

    public BookWhichUserFinishedKey(Book book, User user) {
        this.book = book;
        this.user = user;
    }
}
