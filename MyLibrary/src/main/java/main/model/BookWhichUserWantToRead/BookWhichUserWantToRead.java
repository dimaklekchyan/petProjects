package main.model.BookWhichUserWantToRead;


import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "books_which_users_want_to_read")
public class BookWhichUserWantToRead implements Serializable {

    @EmbeddedId
    private BookWhichUserWantToReadKey key;

    @Column(name = "book_id", updatable = false, insertable = false)
    private int bookId;
    @Column(name = "user_id", updatable = false, insertable = false)
    private int userId;

    public BookWhichUserWantToRead() {
    }

    public BookWhichUserWantToRead(BookWhichUserWantToReadKey key) {
        this.key = key;
    }

    public BookWhichUserWantToReadKey getKey() {
        return key;
    }

    public void setKey(BookWhichUserWantToReadKey key) {
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
