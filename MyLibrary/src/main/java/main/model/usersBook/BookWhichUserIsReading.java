package main.model.usersBook;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Date;

@Data
@Entity
@Table(name = "books_which_users_are_reading")
public class BookWhichUserIsReading implements Serializable {

    @EmbeddedId
    private UsersBookKey key;

    @Column(name = "book_id", updatable = false, insertable = false)
    private long bookId;
    @Column(name = "user_id", updatable = false, insertable = false)
    private long userId;
    @Column(name = "date_added")
    private Date dateAdded;

    public BookWhichUserIsReading() {
    }

    public BookWhichUserIsReading(UsersBookKey key) {
        this.key = key;
        this.dateAdded = new Date(System.currentTimeMillis());
    }
}
