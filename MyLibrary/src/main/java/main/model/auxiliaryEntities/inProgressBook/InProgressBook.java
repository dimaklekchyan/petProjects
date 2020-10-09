package main.model.auxiliaryEntities.inProgressBook;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "in_progress")
public class InProgressBook implements Serializable {

    @EmbeddedId
    private InProgressBookKey key;

    public InProgressBook() {
    }

    public InProgressBook(InProgressBookKey key) {
        this.key = key;
    }

    public InProgressBookKey getKey() {
        return key;
    }

    public void setKey(InProgressBookKey key) {
        this.key = key;
    }
}
