package main.model.auxiliaryEntities.wantToReadBook;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "want_to_read")
public class WantToReadBook implements Serializable {

    @EmbeddedId
    private WantToReadBookKey key;

    public WantToReadBook() {
    }

    public WantToReadBook(WantToReadBookKey key) {
        this.key = key;
    }

    public WantToReadBookKey getKey() {
        return key;
    }

    public void setKey(WantToReadBookKey key) {
        this.key = key;
    }
}
