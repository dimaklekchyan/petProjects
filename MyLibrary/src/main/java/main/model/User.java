package main.model;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;
    private boolean active;
    private String email;
    private String activationCode;

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
    private List<Book> booksWhichUserIsReading;

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

}
