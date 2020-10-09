package main.controller;

import main.model.Book;
import main.model.User;
import main.model.auxiliaryEntities.inProgressBook.InProgressBook;
import main.model.auxiliaryEntities.inProgressBook.InProgressBookKey;
import main.model.auxiliaryEntities.readedBook.ReadedBook;
import main.model.auxiliaryEntities.readedBook.ReadedBookKey;
import main.model.auxiliaryEntities.usersBooks.UsersBooks;
import main.model.auxiliaryEntities.usersBooks.UsersBooksKey;
import main.model.auxiliaryEntities.wantToReadBook.WantToReadBook;
import main.model.auxiliaryEntities.wantToReadBook.WantToReadBookKey;
import main.repository.BookRepository;
import main.repository.UserRepository;
import main.repository.auxiliaryRepository.InProgressRepository;
import main.repository.auxiliaryRepository.ReadedRepository;
import main.repository.auxiliaryRepository.UsersBooksRepository;
import main.repository.auxiliaryRepository.WantToReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UsersBooksRepository usersBooksRepository;
    @Autowired
    private InProgressRepository inProgressRepository;
    @Autowired
    private ReadedRepository readedRepository;
    @Autowired
    private WantToReadRepository wantToReadRepository;


    @GetMapping("/")
    public String greeting(Map<String, Object> model){
        return "greeting";
    }

    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User user,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            Model model){
        List<Book> allBooks = userRepository.findByUsername(user.getUsername()).getBooks();
        List<Book> wantToRead = userRepository.findByUsername(user.getUsername()).getWantToRead();
        List<Book> inProgress = userRepository.findByUsername(user.getUsername()).getInProgress();
        List<Book> readed = userRepository.findByUsername(user.getUsername()).getReaded();

        List<Book> filteredBooks = new ArrayList<>();
        filteredBooks.addAll(allBooks);
        if(title != null && !title.isEmpty()){
            filtration(allBooks, filteredBooks, title);
        }
        if(author != null && !author.isEmpty()){
            filtration(allBooks, filteredBooks, author);
        }

        model.addAttribute("filterTitle", title);
        model.addAttribute("filterAuthor", author);
        model.addAttribute("books", filteredBooks);
        model.addAttribute("wantToRead", wantToRead);
        model.addAttribute("inProgress", inProgress);
        model.addAttribute("readed", readed);

        return "main";
    }

    @PostMapping("/main")
    public String create(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String author){
        Book book = new Book(title, author);
        bookRepository.save(book);
        usersBooksRepository.save(new UsersBooks(new UsersBooksKey(book, user)));
        return "redirect:/main";
    }

    @PostMapping("/main/{id}")
    public String update(
            @AuthenticationPrincipal User user,
            @RequestParam int id,
            @RequestParam String status,
            Model model){
        Book book;
        switch (status){
            case "want":
                book = bookRepository.findById(id);
                checkingTheBookAgainstLists(inProgressRepository, readedRepository, wantToReadRepository, userRepository, book, user);
                wantToReadRepository.save(new WantToReadBook(new WantToReadBookKey(book, user)));
                break;
            case "read":
                book = bookRepository.findById(id);
                checkingTheBookAgainstLists(inProgressRepository, readedRepository, wantToReadRepository, userRepository, book, user);
                inProgressRepository.save(new InProgressBook(new InProgressBookKey(book, user)));
                break;
            case "readed":
                book = bookRepository.findById(id);
                checkingTheBookAgainstLists(inProgressRepository, readedRepository, wantToReadRepository, userRepository, book, user);
                readedRepository.save(new ReadedBook(new ReadedBookKey(book, user)));
                break;
        }
        return "redirect:/main";
    }

    private static void checkingTheBookAgainstLists(InProgressRepository inProgressRepository,
                                                    ReadedRepository readedRepository,
                                                    WantToReadRepository wantToReadRepository,
                                                    UserRepository userRepository,
                                                    Book book, User user){

        if(userRepository.findByUsername(user.getUsername()).getInProgress().contains(book)){
            inProgressRepository.delete(new InProgressBook(new InProgressBookKey(book, user)));
        }

        if(userRepository.findByUsername(user.getUsername()).getReaded().contains(book)){
            readedRepository.delete(new ReadedBook(new ReadedBookKey(book, user)));
        }

        if(userRepository.findByUsername(user.getUsername()).getWantToRead().contains(book)){
            wantToReadRepository.delete(new WantToReadBook(new WantToReadBookKey(book, user)));
        }
    }

    private static void filtration(List<Book> originalList, List<Book> finalList, String field){
        for (Book book : originalList){
            if(!book.getAuthor().equals(field)){
                finalList.remove(book);
            }
        }
    }
}
