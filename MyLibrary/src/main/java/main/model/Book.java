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

//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "users_books",
//            joinColumns = {@JoinColumn(name = "book_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")}
//    )
//    private List<User> users;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }
}
