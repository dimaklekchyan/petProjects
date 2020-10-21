package main.model.auxiliaryEntities.usersBook;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users_books")
public class UsersBook implements Serializable {

    @EmbeddedId
    private UsersBookKey key;

    @Column(name = "book_id", updatable = false, insertable = false)
    private int bookId;
    @Column(name = "user_id", updatable = false, insertable = false)
    private int userId;

    public UsersBook() {
    }

    public UsersBook(UsersBookKey key) {
        this.key = key;
    }

    public UsersBookKey getKey() {
        return key;
    }

    public void setKey(UsersBookKey key) {
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
