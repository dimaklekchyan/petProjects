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
import main.repository.auxiliaryRepository.BookWhichUserWantToReadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/search")
public class SearchController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookWhichUserAreReadingRepository bookWhichUserAreReadingRepository;
    @Autowired
    private BookWhichUserFinishedRepository bookWhichUserFinishedRepository;
    @Autowired
    private BookWhichUserWantToReadRepository bookWhichUserWantToReadRepository;

    @GetMapping()
    public String searchBooks(@RequestParam(required = false) String title,
                              @RequestParam(required = false) String author,
                              Model model){
        if(title != null){title = title.trim();}
        if(author != null){author = author.trim();}
        Iterable<Book> books = bookRepository.findByTitleOrAuthor(title, author);
        model.addAttribute("books", books);
        return "/search";
    }

    @PostMapping("/{id}")
    public String addBookToAnyList(@AuthenticationPrincipal User user,
                                   @RequestParam int id,
                                   @RequestParam String list,
                                   Model model){
        Book book = bookRepository.findById(id);
        switch (list){
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
        return "/search";
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
