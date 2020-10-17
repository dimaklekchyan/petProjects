package main.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private boolean active;

    //Все книги юзера
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_books",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> allBooks;

    //Книги, котороые хочется прочитать
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_which_users_want_to_read",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> booksWhichUserWantToRead;

    //Книги юзера в процессе
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_which_users_are_reading",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> booksWhichUserAreReading;

    //Прочитаныне книги юзера
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_which_users_finished",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> booksWhichUserFinished;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Book> getAllBooks() {
        return allBooks;
    }

    public void setAllBooks(List<Book> allBooks) {
        this.allBooks = allBooks;
    }

    public List<Book> getBooksWhichUserWantToRead() {
        return booksWhichUserWantToRead;
    }

    public void setBooksWhichUserWantToRead(List<Book> booksWhichUserWantToRead) {
        this.booksWhichUserWantToRead = booksWhichUserWantToRead;
    }

    public List<Book> getBooksWhichUserAreReading() {
        return booksWhichUserAreReading;
    }

    public void setBooksWhichUserAreReading(List<Book> booksWhichUserAreReading) {
        this.booksWhichUserAreReading = booksWhichUserAreReading;
    }

    public List<Book> getBooksWhichUserFinished() {
        return booksWhichUserFinished;
    }

    public void setBooksWhichUserFinished(List<Book> booksWhichUserFinished) {
        this.booksWhichUserFinished = booksWhichUserFinished;
    }
}
