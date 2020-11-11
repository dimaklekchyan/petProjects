package main.controller;

import main.model.Book;
import main.model.ListsOfBooks;
import main.model.User;
import main.repository.*;
import main.repository.UsersBookRepository;
import main.service.BookService;
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
    private BookService bookService;



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

        List<Book> booksWhichUserWantToRead = usersBookRepository.findByTitleOrAuthorAndList(title, author, user.getId(), ListsOfBooks.WANT_TO_READ);
        List<Book> booksWhichUserIsReading = usersBookRepository.findByTitleOrAuthorAndList(title, author, user.getId(), ListsOfBooks.IS_READING);
        List<Book> booksWhichUserFinished = usersBookRepository.findByTitleOrAuthorAndList(title, author, user.getId(), ListsOfBooks.FINISHED);

        model.addAttribute("wantToRead", booksWhichUserWantToRead);
        model.addAttribute("isReading", booksWhichUserIsReading);
        model.addAttribute("finished", booksWhichUserFinished);
        return "main";
    }

    @PostMapping("/main/{id}")
    public String update(
            @AuthenticationPrincipal User user,
            @RequestParam int id,
            @RequestParam String oldList,
            @RequestParam String newList){
        Book book = bookRepository.findById(id);
        bookService.changeList(book, user, newList);
        return "redirect:/main";
    }
}
