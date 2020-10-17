package main.controller;

import main.model.Book;
import main.model.User;
import main.model.auxiliaryEntities.BookWhichUserAreReading.BookWhichUserAreReading;
import main.model.auxiliaryEntities.BookWhichUserAreReading.BookWhichUserAreReadingKey;
import main.model.auxiliaryEntities.BookWhichUserFinished.BookWhichUserFinished;
import main.model.auxiliaryEntities.BookWhichUserFinished.BookWhichUserFinishedKey;
import main.model.auxiliaryEntities.BookWhichUserWantToRead.BookWhichUserWantToRead;
import main.model.auxiliaryEntities.BookWhichUserWantToRead.BookWhichUserWantToReadKey;
import main.repository.BookRepository;
import main.repository.UserRepository;
import main.repository.auxiliaryRepository.BookWhichUserAreReadingRepository;
import main.repository.auxiliaryRepository.BookWhichUserFinishedRepository;
import main.repository.auxiliaryRepository.UsersBookRepository;
import main.repository.auxiliaryRepository.BookWhichUserWantToReadRepository;
import main.service.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UsersBookRepository usersBookRepository;
    @Autowired
    private BookWhichUserAreReadingRepository bookWhichUserAreReadingRepository;
    @Autowired
    private BookWhichUserFinishedRepository bookWhichUserFinishedRepository;
    @Autowired
    private BookWhichUserWantToReadRepository bookWhichUserWantToReadRepository;


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
        List<Book> booksWhichUserWantToRead = userRepository.findByUsername(user.getUsername()).getBooksWhichUserWantToRead();
        List<Book> booksWhichUserAreReading = userRepository.findByUsername(user.getUsername()).getBooksWhichUserAreReading();
        List<Book> booksWhichUserFinished = userRepository.findByUsername(user.getUsername()).getBooksWhichUserFinished();

        booksWhichUserWantToRead = Filter.filtration(title, author, booksWhichUserWantToRead);
        booksWhichUserAreReading = Filter.filtration(title, author, booksWhichUserAreReading);
        booksWhichUserFinished = Filter.filtration(title, author, booksWhichUserFinished);

        model.addAttribute("wantsToRead", booksWhichUserWantToRead);
        model.addAttribute("isReading", booksWhichUserAreReading);
        model.addAttribute("read", booksWhichUserFinished);
        return "main";
    }

    @PostMapping("/main")
    public String create(
            @AuthenticationPrincipal User user,
            @RequestParam String title,
            @RequestParam String author,
            Model model){

        List<Book> suitableBooks = bookRepository.findByTitleOrAuthor(title, author);
        model.addAttribute("suitableBooks", suitableBooks);
        return "redirect:/main";
    }

    @PostMapping("/main/{id}")
    public String update(
            @AuthenticationPrincipal User user,
            @RequestParam int id,
            @RequestParam String status,
            Model model){
        Book book = bookRepository.findById(id);
        switch (status){
            case "want":
                checkingTheBookAgainstLists(bookWhichUserAreReadingRepository, bookWhichUserFinishedRepository, bookWhichUserWantToReadRepository, userRepository, book, user);
                bookWhichUserWantToReadRepository.save(new BookWhichUserWantToRead(new BookWhichUserWantToReadKey(book, user)));
                break;
            case "read":
                checkingTheBookAgainstLists(bookWhichUserAreReadingRepository, bookWhichUserFinishedRepository, bookWhichUserWantToReadRepository, userRepository, book, user);
                bookWhichUserAreReadingRepository.save(new BookWhichUserAreReading(new BookWhichUserAreReadingKey(book, user)));
                break;
            case "readed":
                checkingTheBookAgainstLists(bookWhichUserAreReadingRepository, bookWhichUserFinishedRepository, bookWhichUserWantToReadRepository, userRepository, book, user);
                bookWhichUserFinishedRepository.save(new BookWhichUserFinished(new BookWhichUserFinishedKey(book, user)));
                break;
        }
        return "redirect:/main";
    }

    private static void checkingTheBookAgainstLists(BookWhichUserAreReadingRepository bookWhichUserAreReadingRepository,
                                                    BookWhichUserFinishedRepository bookWhichUserFinishedRepository,
                                                    BookWhichUserWantToReadRepository bookWhichUserWantToReadRepository,
                                                    UserRepository userRepository,
                                                    Book book, User user){

        if(userRepository.findByUsername(user.getUsername()).getBooksWhichUserAreReading().contains(book)){
            bookWhichUserAreReadingRepository.delete(new BookWhichUserAreReading(new BookWhichUserAreReadingKey(book, user)));
        }

        if(userRepository.findByUsername(user.getUsername()).getBooksWhichUserFinished().contains(book)){
            bookWhichUserFinishedRepository.delete(new BookWhichUserFinished(new BookWhichUserFinishedKey(book, user)));
        }

        if(userRepository.findByUsername(user.getUsername()).getBooksWhichUserWantToRead().contains(book)){
            bookWhichUserWantToReadRepository.delete(new BookWhichUserWantToRead(new BookWhichUserWantToReadKey(book, user)));
        }
    }
}
