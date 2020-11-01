package main.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Data
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;
    private String author;
    @Column(name="year_of_writing", nullable = true)
    private Date yearOfWriting;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_books",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_which_users_want_to_read",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> usersWhoWillRead;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_which_users_are_reading",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> usersWhoAreReading;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "books_which_users_finished",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private List<User> usersWhoFinished;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
