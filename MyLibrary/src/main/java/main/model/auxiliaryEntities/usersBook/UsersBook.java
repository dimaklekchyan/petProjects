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

    @Column(name = "user_id", insertable = false, updatable = false)
    private long userId;

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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
