package main.model.auxiliaryEntities.BookWhichUserAreReading;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "books_which_users_are_reading")
public class BookWhichUserAreReading implements Serializable {

    @EmbeddedId
    private BookWhichUserAreReadingKey key;

    public BookWhichUserAreReading() {
    }

    public BookWhichUserAreReading(BookWhichUserAreReadingKey key) {
        this.key = key;
    }

    public BookWhichUserAreReadingKey getKey() {
        return key;
    }

    public void setKey(BookWhichUserAreReadingKey key) {
        this.key = key;
    }
}
