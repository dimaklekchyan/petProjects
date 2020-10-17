package main.model.auxiliaryEntities.BookWhichUserFinished;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "books_which_users_finished")
public class BookWhichUserFinished implements Serializable {

    @EmbeddedId
    private BookWhichUserFinishedKey key;

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
}
