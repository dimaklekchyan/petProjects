package main.model.auxiliaryEntities.BookWhichUserWantToRead;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "books_which_users_want_to_read")
public class BookWhichUserWantToRead implements Serializable {

    @EmbeddedId
    private BookWhichUserWantToReadKey key;

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
}
