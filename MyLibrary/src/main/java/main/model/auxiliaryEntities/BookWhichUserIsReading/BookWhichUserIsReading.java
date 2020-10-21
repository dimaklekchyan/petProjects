package main.model.auxiliaryEntities.BookWhichUserIsReading;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "books_which_users_are_reading")
public class BookWhichUserIsReading implements Serializable {

    @EmbeddedId
    private BookWhichUserIsReadingKey key;

    @Column(name = "book_id", updatable = false, insertable = false)
    private int bookId;
    @Column(name = "user_id", updatable = false, insertable = false)
    private int userId;

    public BookWhichUserIsReading() {
    }

    public BookWhichUserIsReading(BookWhichUserIsReadingKey key) {
        this.key = key;
    }

    public BookWhichUserIsReadingKey getKey() {
        return key;
    }

    public void setKey(BookWhichUserIsReadingKey key) {
        this.key = key;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
