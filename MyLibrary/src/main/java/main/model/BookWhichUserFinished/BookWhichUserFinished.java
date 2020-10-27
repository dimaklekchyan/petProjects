package main.model.BookWhichUserFinished;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Entity
@Table(name = "books_which_users_finished")
public class BookWhichUserFinished implements Serializable {

    @EmbeddedId
    private BookWhichUserFinishedKey key;

    @Column(name = "book_id", updatable = false, insertable = false)
    private long bookId;
    @Column(name = "user_id", updatable = false, insertable = false)
    private long userId;
    @Column(name = "date_added")
    private Date dateAdded;

    public BookWhichUserFinished() {
    }

    public BookWhichUserFinished(BookWhichUserFinishedKey key) {
        this.key = key;
        this.dateAdded = new Date(System.currentTimeMillis());
    }

    public BookWhichUserFinishedKey getKey() {
        return key;
    }

    public void setKey(BookWhichUserFinishedKey key) {
        this.key = key;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
