package main.model.auxiliaryEntities.usersBooks;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "users_books")
public class UsersBooks implements Serializable {

    @EmbeddedId
    private UsersBooksKey key;

    @Column(name = "user_id", insertable = false, updatable = false)
    private long userId;

    public UsersBooks() {
    }

    public UsersBooks(UsersBooksKey key) {
        this.key = key;
    }

    public UsersBooksKey getKey() {
        return key;
    }

    public void setKey(UsersBooksKey key) {
        this.key = key;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
