package main.model.auxiliaryEntities.readedBook;

import main.model.Book;
import main.model.User;

import javax.persistence.Embeddable;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
public class ReadedBookKey implements Serializable {

    @OneToOne
    private Book book;
    @OneToOne
    private User user;

    public ReadedBookKey() {
    }

    public ReadedBookKey(Book book, User user) {
        this.book = book;
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
