package main.model.BookWhichUserFinished;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "books_which_users_finished")
public class BookWhichUserFinished implements Serializable {

    @EmbeddedId
    private BookWhichUserFinishedKey key;

    @Column(name = "book_id", updatable = false, insertable = false)
    private int bookId;
    @Column(name = "user_id", updatable = false, insertable = false)
    private int userId;

    public BookWhichUserFinished() {
    }

    public BookWhichUserFinished(BookWhichUserFinishedKey key) {
        this.key = key;
    }

    public BookWhichUserFinishedKey getKey() {
        return key;
    }

    public void setKey(BookWhichUserFinishedKey key) {
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
