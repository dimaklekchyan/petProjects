package main.model.auxiliaryEntities.readedBook;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "readed")
public class ReadedBook implements Serializable {

    @EmbeddedId
    private ReadedBookKey key;

    public ReadedBook() {
    }

    public ReadedBook(ReadedBookKey key) {
        this.key = key;
    }

    public ReadedBookKey getKey() {
        return key;
    }

    public void setKey(ReadedBookKey key) {
        this.key = key;
    }
}
